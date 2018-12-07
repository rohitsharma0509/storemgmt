package com.app.myproject.dto.query;

public class SelectField {
	private Object field;
	private Integer joinIndex;

	public Object getField() {
		return field;
	}

	public void setFields(Object field) {
		this.field = field;
	}

	public Integer getJoinIndex() {
		return joinIndex;
	}

	public void setJoinIndex(Integer joinIndex) {
		this.joinIndex = joinIndex;
	}
}
