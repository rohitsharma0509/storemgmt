package com.app.myproject.dto.query;

public class WhereCondition {
	private String key;
	private Object value;
	private String operation;
	private Integer joinIndex;

	public WhereCondition(String key, Object value, String operation,
			Integer joinIndex) {
		super();
		this.key = key;
		this.value = value;
		this.operation = operation;
		this.joinIndex = joinIndex;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getJoinIndex() {
		return joinIndex;
	}

	public void setJoinIndex(Integer joinIndex) {
		this.joinIndex = joinIndex;
	}
}
