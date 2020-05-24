package com.panc.bank.action.out;

import java.util.List;

public class EntityOut<T> {

	private String status;
	private List<ErrorEntity> errors;
	private T payload;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ErrorEntity> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorEntity> errors) {
		this.errors = errors;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EntityOut [status=");
		builder.append(status);
		builder.append(", errors=");
		builder.append(errors);
		builder.append(", payload=");
		builder.append(payload);
		builder.append("]");
		return builder.toString();
	}
}
