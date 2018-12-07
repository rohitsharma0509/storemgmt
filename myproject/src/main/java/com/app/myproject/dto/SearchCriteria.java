package com.app.myproject.dto;

public class SearchCriteria {
	private String key;
	private Object value;
	private String operation;

	public SearchCriteria(String key, Object value, String operation) {
		this.key = key;
		this.value = value;
		this.operation = operation;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public String getOperation() {
		return operation;
	}
}
