package com.panc.bank.action;

import com.panc.bank.action.out.EntityOut;

public interface CommandAction <I,O>{
	
	public EntityOut<O> execute(I input);

}
