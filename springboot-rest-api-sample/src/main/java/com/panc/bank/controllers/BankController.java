package com.panc.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping(value = "/bank")
public class BankController {
	
	@Autowired
	private LetturaSaldoAction letturaSaldo;
	
	@Autowired
	private BonificoAction bonifico;
	
	@Autowired
	private LetturaTransazioniAction letturaTransazioni;
	
    @RequestMapping(value = "/letturaSaldo/{idAccount}", method = RequestMethod.GET)
    public EntityOut<LetturaSaldoOut> letturaSaldo(@PathVariable String idAccount) {
    	LetturaSaldoIn in = new LetturaSaldoIn();
    	in.setAccountId(idAccount);
    	
        return letturaSaldo.execute(in);
    }
    
    @RequestMapping(value = "/bonifico/{idAccount}", method = RequestMethod.POST)
    public EntityOut<BonificoOut> bonifico(@PathVariable String idAccount, @RequestBody BonificoIn in) {
        return bonifico.execute(in);
    }
    

    @RequestMapping(value = "/letturaTransazioni/{idAccount}/{fromAccountingDate}/{toAccountingDate}", method = RequestMethod.GET)
    public EntityOut<LetturaTransazioniOut> letturaTransazioni(@PathVariable String idAccount, @PathVariable String fromAccountingDate, @PathVariable String toAccountingDate) {
    	LetturaTransazioniIn in = new LetturaTransazioniIn();
    	in.setAccountId(idAccount);
    	in.setFromAccountingDate(fromAccountingDate);
    	in.setToAccountingDate(toAccountingDate);
        return letturaTransazioni.execute(in);
    }
}
