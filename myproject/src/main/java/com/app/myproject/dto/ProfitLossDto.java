package com.app.myproject.dto;


public class ProfitLossDto {
	private String orderDate;
	
	private Integer noOfOrders;
	
	private Integer soldQuantity;
	
	private Double amountReceived;
	
	private Double profitOrLoss;

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getNoOfOrders() {
		return noOfOrders;
	}

	public void setNoOfOrders(Integer noOfOrders) {
		this.noOfOrders = noOfOrders;
	}

	public Integer getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(Integer soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Double getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(Double amountReceived) {
		this.amountReceived = amountReceived;
	}

	public Double getProfitOrLoss() {
		return profitOrLoss;
	}

	public void setProfitOrLoss(Double profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
	}
}
