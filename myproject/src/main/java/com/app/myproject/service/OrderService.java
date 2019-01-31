package com.app.myproject.service;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.CustomerDto;
import com.app.myproject.dto.OrderDto;
import com.app.myproject.dto.ProductDto;
import com.app.myproject.model.Order;

public interface OrderService {
	OrderDto addOrder(java.util.List<ProductDto> productDtos, CustomerDto customerDto, Double totalPrice);

	OrderDto getOrder(Long id);

	Page<Order> getOrders(Pageable pageable);
	
	ByteArrayOutputStream createOrderPdf(Long id);

	Long countByOrderDate(ZonedDateTime orderDate);
	
	Long countByOrderDateGreaterThanEqual(ZonedDateTime orderDate);

	CustomPage<OrderDto> searchOrders(Order order, Pageable pageable);
}
