package com.panc.bank;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.panc.bank.action.bonifico.BonificoAction;
import com.panc.bank.action.bonifico.in.BonificoIn;
import com.panc.bank.action.bonifico.out.BonificoOut;
import com.panc.bank.action.letturaSaldo.LetturaSaldoAction;
import com.panc.bank.action.letturaSaldo.in.LetturaSaldoIn;
import com.panc.bank.action.letturaSaldo.out.LetturaSaldoOut;
import com.panc.bank.action.letturaTransazioni.LetturaTransazioniAction;
import com.panc.bank.action.letturaTransazioni.in.LetturaTransazioniIn;
import com.panc.bank.action.letturaTransazioni.out.LetturaTransazioniOut;
import com.panc.bank.action.out.EntityOut;
import com.panc.bank.util.InputTastiera;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Auth-Schema", "S2S");
		headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
		return headers;
	}

	@Autowired
	private LetturaSaldoAction letturaSaldo;

	@Autowired
	private BonificoAction bonifico;

	@Autowired
	private LetturaTransazioniAction letturaTransazioni;

	@Override
	public void run(String... args) throws Exception {

		for (;;) {
			System.out.println("Premere il numero dell'operazione richiesta");
			System.out.println("1 - Lettura Saldo");
			System.out.println("2 - Bonifico");
			System.out.println("3 - Lettura Transazione");

			int operation = InputTastiera.getInstance().getInt();
			switch (operation) {
			case 1:
				for (;;) {
					Class<LetturaSaldoIn> classe = LetturaSaldoIn.class;
					LetturaSaldoIn letturaSaldoIn = new LetturaSaldoIn();
					Field[] fields = classe.getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						Field field = fields[i];
						System.out.print("Inserire " + field.getName() + ":");
						field.setAccessible(true);
						try {
							if (String.class.equals(field.getType()))
								field.set(letturaSaldoIn, InputTastiera.getInstance().getString());
							else {
								field.set(letturaSaldoIn, InputTastiera.getInstance().getInt());
							}
						} catch (Exception e) {
							System.out.println("Il valore per il campo non è valido!");
							System.out.println("1 - riprova");
							System.out.println("qualsiasi carattere - Torna a menu principale");
							String c = InputTastiera.getInstance().getString();
							if ("1".equals(c)) {
								i--;
							} else {
								break;
							}
						}
					}
					EntityOut<LetturaSaldoOut> out = letturaSaldo.execute(letturaSaldoIn);
					if ("OK".equals(out.getStatus()))
						System.out.println(out.getPayload().toString());
					else {
						System.out.println(out.getErrors().toString());
					}
					break;
				}
				break;
			case 2:
				for (;;) {
					Class<BonificoIn> classe = BonificoIn.class;
					BonificoIn bonificoIn = new BonificoIn();
					Field[] fields = classe.getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						Field field = fields[i];
						System.out.print("Inserire " + field.getName() + ":");
						field.setAccessible(true);
						try {
							if (String.class.equals(field.getType()))
								field.set(bonificoIn, InputTastiera.getInstance().getString());
							else {
								field.set(bonificoIn, InputTastiera.getInstance().getInt());
							}
						} catch (Exception e) {
							System.out.println("Il valore per il campo non è valido!");
							System.out.println("1 - riprova");
							System.out.println("qualsiasi carattere - Torna a menu principale");
							String c = InputTastiera.getInstance().getString();
							if ("1".equals(c)) {
								i--;
							} else {
								break;
							}
						}
					}
					EntityOut<BonificoOut> out = bonifico.execute(bonificoIn);
					if ("OK".equals(out.getStatus()))
						System.out.println(out.getPayload().toString());
					else {
						System.out.println(out.getErrors().toString());
					}
					break;
				}
				break;
			case 3:
				for (;;) {
					Class<LetturaTransazioniIn> classe = LetturaTransazioniIn.class;
					LetturaTransazioniIn letturaTransazioniIn = new LetturaTransazioniIn();
					Field[] fields = classe.getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						Field field = fields[i];
						System.out.print("Inserire " + field.getName() + ":");
						field.setAccessible(true);
						try {
							if (String.class.equals(field.getType()))
								field.set(letturaTransazioniIn, InputTastiera.getInstance().getString());
							else {
								field.set(letturaTransazioniIn, InputTastiera.getInstance().getInt());
							}
						} catch (Exception e) {
							System.out.println("Il valore per il campo non è valido!");
							System.out.println("1 - riprova");
							System.out.println("qualsiasi carattere - Torna a menu principale");
							String c = InputTastiera.getInstance().getString();
							if ("1".equals(c)) {
								i--;
							} else {
								break;
							}
						}
					}
					EntityOut<LetturaTransazioniOut> out = letturaTransazioni.execute(letturaTransazioniIn);
					if ("OK".equals(out.getStatus()))
						System.out.println(out.getPayload().toString());
					else {
						System.out.println(out.getErrors().toString());
					}
					break;
				}
			}
		}
	}
}
