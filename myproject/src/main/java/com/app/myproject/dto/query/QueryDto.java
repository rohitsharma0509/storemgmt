package com.app.myproject.dto.query;

import java.util.List;

public class QueryDto {
	private List<SelectField> selectFields;
	private List<TableJoin> tableJoins;
	private List<WhereCondition> whereConditions;
	private GroupByFields groupByFields;
	private HavingConditions havingConditions;
	private OrderByFields orderByFields;
	private Integer offset;
	private Integer limit;

	public List<SelectField> getSelectFields() {
		return selectFields;
	}

	public void setSelectFields(List<SelectField> selectFields) {
		this.selectFields = selectFields;
	}

	public List<TableJoin> getTableJoins() {
		return tableJoins;
	}

	public void setTableJoins(List<TableJoin> tableJoins) {
		this.tableJoins = tableJoins;
	}

	public List<WhereCondition> getWhereConditions() {
		return whereConditions;
	}

	public void setWhereConditions(List<WhereCondition> whereConditions) {
		this.whereConditions = whereConditions;
	}

	public GroupByFields getGroupByFields() {
		return groupByFields;
	}

	public void setGroupByFields(GroupByFields groupByFields) {
		this.groupByFields = groupByFields;
	}

	public HavingConditions getHavingConditions() {
		return havingConditions;
	}

	public void setHavingConditions(HavingConditions havingConditions) {
		this.havingConditions = havingConditions;
	}

	public OrderByFields getOrderByFields() {
		return orderByFields;
	}

	public void setOrderByFields(OrderByFields orderByFields) {
		this.orderByFields = orderByFields;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
