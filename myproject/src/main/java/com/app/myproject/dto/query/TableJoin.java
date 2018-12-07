package com.app.myproject.dto.query;

import javax.persistence.criteria.JoinType;

public class TableJoin {
	private String entityName;
	private JoinType joinType;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}
}
