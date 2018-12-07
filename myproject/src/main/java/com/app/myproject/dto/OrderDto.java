package com.app.myproject.dto;

import java.util.HashSet;
import java.util.Set;

public class OrderDto {
	private Long id;

	private String orderNumber;

	private Double totalAmount;

	private String orderDate;

	private CustomerDto customerDto;

	private Set<ProductDto> productDtos = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}

	public Set<ProductDto> getProductDtos() {
		return productDtos;
	}

	public void setProductDtos(Set<ProductDto> productDtos) {
		this.productDtos = productDtos;
	}

}
