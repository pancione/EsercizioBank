package com.panc.bank.action.bonifico;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panc.bank.action.CommandAction;
import com.panc.bank.action.bonifico.in.BonificoIn;
import com.panc.bank.action.bonifico.out.BonificoOut;
import com.panc.bank.action.exception.InputException;
import com.panc.bank.action.letturaSaldo.out.LetturaSaldoOut;
import com.panc.bank.action.out.EntityOut;
import com.panc.bank.action.out.ErrorEntity;

@Service
public class BonificoAction implements CommandAction<BonificoIn, BonificoOut>{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders header;
	
	@Autowired
	private ObjectMapper objectMapper;

	private static String url ="https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
	
	@Override
	public EntityOut<BonificoOut> execute(BonificoIn input) {
		try {
			validate(input);
			Map<String,String> map = new HashMap<>();
			map.put("accountId", input.getAccountId()+"");
			HttpEntity<String> request = new HttpEntity<>(header);
			ResponseEntity<EntityOut<BonificoOut>> response = null;
			response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<EntityOut<BonificoOut>>() {}, map);			
			return response.getBody();			
		}catch (HttpClientErrorException e) {
			EntityOut<BonificoOut> out = null;
			try {
				out = objectMapper.readValue(e.getResponseBodyAsString(), new TypeReference<EntityOut<LetturaSaldoOut>>() {});
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
			EntityOut<BonificoOut> out = new EntityOut<BonificoOut>();
			List<ErrorEntity> errors = new ArrayList<ErrorEntity>();
			ErrorEntity error = new ErrorEntity();
			error.setDescription(e.getMessage());
			errors.add(error);
			out.setErrors(errors);

			return out;
		}
	}
	
	private void validate(BonificoIn input) throws InputException {
		if(!input.getExecutionDate().matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$"))
			throw new InputException("ExecutionDate non valida (DD/MM/YYYY)"); 
		
	}

}
