package com.panc.bank.action.letturaTransazioni.out;

import java.util.List;

public class LetturaTransazioniOut {
	List<Transaction> list;

	public List<Transaction> getList() {
		return list;
	}

	public void setList(List<Transaction> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LetturaTransazioniOut [list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
	
}
