package com.panc.bank.action.letturaSaldo;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panc.bank.action.CommandAction;
import com.panc.bank.action.exception.InputException;
import com.panc.bank.action.letturaSaldo.in.LetturaSaldoIn;
import com.panc.bank.action.letturaSaldo.out.LetturaSaldoOut;
import com.panc.bank.action.out.EntityOut;
import com.panc.bank.action.out.ErrorEntity;

@Service("LetturaSaldo")
public class LetturaSaldoAction implements CommandAction<LetturaSaldoIn, LetturaSaldoOut>{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders header;
	
	@Autowired
	private ObjectMapper objectMapper;

	private static String url ="https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}";
	
	@Override
	public EntityOut<LetturaSaldoOut> execute(LetturaSaldoIn input) {
		try {
			validate(input);
			Map<String,String> map = new HashMap<>();
			map.put("accountId", input.getAccountId());
			HttpEntity<String> request = new HttpEntity<>(header);
			ResponseEntity<EntityOut<LetturaSaldoOut>> response = null;
			response = restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<EntityOut<LetturaSaldoOut>>() {}, map);	
			return response.getBody();			
		}catch (HttpClientErrorException e) {
			EntityOut<LetturaSaldoOut> out = null;
			try {
				out = objectMapper.readValue(e.getResponseBodyAsString(), new TypeReference<EntityOut<LetturaSaldoOut>>() {});
			} catch (Exception e1) {		
				e1.printStackTrace();
			}
			return out;
		} catch (InputException e) {
			EntityOut<LetturaSaldoOut> out = new EntityOut<LetturaSaldoOut>();
			List<ErrorEntity> errors = new ArrayList<ErrorEntity>();
			ErrorEntity error = new ErrorEntity();
			error.setDescription(e.getMessage());
			errors.add(error);
			out.setErrors(errors);

			return out;
		}
		
	}
	
	public void validate(LetturaSaldoIn input) throws InputException {
		try {
			Integer.parseInt(input.getAccountId());
		} catch (Exception e) {
			throw new InputException("Valore AccountId non valido"); 
		}
	}

}
