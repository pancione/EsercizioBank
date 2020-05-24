package com.panc.bank.action.letturaTransazioni;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panc.bank.action.CommandAction;
import com.panc.bank.action.exception.InputException;
import com.panc.bank.action.letturaTransazioni.in.LetturaTransazioniIn;
import com.panc.bank.action.letturaTransazioni.out.LetturaTransazioniOut;
import com.panc.bank.action.out.EntityOut;
import com.panc.bank.action.out.ErrorEntity;

@Service
public class LetturaTransazioniAction implements CommandAction<LetturaTransazioniIn, LetturaTransazioniOut>{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders header;
	
	@Autowired
	private ObjectMapper objectMapper;

	private static String url ="https://sandbox.platfr.io//api/gbs/banking/v4.0/accounts/{accountId}/transactions";
	
	@Override
	public EntityOut<LetturaTransazioniOut> execute(LetturaTransazioniIn input) {

		try {
			validate(input);
			HttpEntity<String> request = new HttpEntity<>(header);
			
			Map<String, String> urlParams = new HashMap<String, String>();
			urlParams.put("accountId", input.getAccountId());
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
					.queryParam("fromAccountingDate", input.getFromAccountingDate())
					.queryParam("toAccountingDate", input.getToAccountingDate());
			ResponseEntity<EntityOut<LetturaTransazioniOut>> response = null;
			response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET,request, new ParameterizedTypeReference<EntityOut<LetturaTransazioniOut>>() {});			
			return response.getBody();			
		}catch (HttpClientErrorException e) {
			EntityOut<LetturaTransazioniOut> out = null;
			try {
				out = objectMapper.readValue(e.getResponseBodyAsString(), new TypeReference<EntityOut<LetturaTransazioniOut>>() {});
			} catch (JsonParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return out;
		}catch (InputException e) {
			EntityOut<LetturaTransazioniOut> out = new EntityOut<LetturaTransazioniOut>();
			List<ErrorEntity> errors = new ArrayList<ErrorEntity>();
			ErrorEntity error = new ErrorEntity();
			error.setDescription(e.getMessage());
			errors.add(error);
			out.setErrors(errors);

			return out;
		}
		
	}
	
	private void validate(LetturaTransazioniIn input) throws InputException {
		if(!input.getFromAccountingDate().matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))") || !input.getToAccountingDate().matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"))
	    	throw new InputException("Formato Data non valida (YYYY-MM-DD)"); 
		
	}
}
