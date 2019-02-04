package com.app.myproject.mapper;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.app.myproject.constants.Constants;
import com.app.myproject.dto.CustomerDto;
import com.app.myproject.dto.OrderDto;
import com.app.myproject.dto.ProductDto;
import com.app.myproject.dto.ShoppingCart;
import com.app.myproject.model.Customer;
import com.app.myproject.model.Order;
import com.app.myproject.model.OrderDetails;
import com.app.myproject.model.Product;
import com.app.myproject.service.ProductService;
import com.app.myproject.util.CommonUtil;

@Component
public class OrderMapper {

	@Inject
	private ProductService productService;

	@Inject
	private ProductMapper productMapper;

	@Inject
	private CommonUtil commonUtil;
	
	public Order convertToOrder(java.util.List<ProductDto> productDtos, CustomerDto customerDto, Double totalAmount) {
		Order order = new Order();
		order.setOrderDate(ZonedDateTime.now());
		order.setOrderNumber(commonUtil.generateRandomDigits("ORD", 10, ""));
		order.setTotalAmount(totalAmount);
		order.setCustomer(customerDtoToCustomer(customerDto));
		order.setOrderDetails(productDtosToOrderDetails(productDtos, order));
		return order;
	}
	

	public Order shoppingCartToOrder(ShoppingCart shoppingCart) {
		Order order = new Order();
		order.setOrderDate(ZonedDateTime.now());
		order.setOrderNumber(commonUtil.generateRandomDigits("ORD", 10, ""));
		order.setTotalAmount(shoppingCart.getTotalPrice());
		order.setCustomer(customerDtoToCustomer(shoppingCart.getCustomerDto()));
		order.setOrderDetails(productDtosToOrderDetails(
				shoppingCart.getProductDtos(), order));
		return order;
	}
	
	public List<OrderDto> ordersToOrderDtos(List<Order> orders) {
		if(CollectionUtils.isEmpty(orders)){
			Collections.emptyList();
		}
		List<OrderDto> orderDtos = new ArrayList<>();
		orders.stream().filter(Objects::nonNull).forEach(order -> {
			orderDtos.add(orderToOrderDto(order));
		});
		return orderDtos;
	}

	public OrderDto orderToOrderDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setOrderDate(commonUtil.convertZonedDateTimeToString(order.getOrderDate(), Constants.DATETIME_FORMAT_YYYYMMDDHHMMSS));
		orderDto.setOrderNumber(order.getOrderNumber());
		orderDto.setTotalAmount(order.getTotalAmount());
		orderDto.setCustomerDto(customerToCustomerDto(order.getCustomer()));
		orderDto.setProductDtos(orderDetailsToProductDtos(order.getOrderDetails()));
		return orderDto;
	}

	public Set<ProductDto> orderDetailsToProductDtos(
			Set<OrderDetails> orderDetails) {
		Set<ProductDto> productDtos = new HashSet<>();
		orderDetails.stream().filter(Objects::nonNull).forEach(orderDetail -> {
			ProductDto productDto = new ProductDto();
			productDto.setQuantity(orderDetail.getQuantity());
			Product product = orderDetail.getProduct();
			productDto.setName(product.getName());
			productDto.setCode(product.getCode());
			productDto.setPerProductPrice(product.getPerProductPrice());
			productDtos.add(productDto);
		});
		return productDtos;
	}

	private Set<OrderDetails> productDtosToOrderDetails(
			List<ProductDto> productDtos, Order order) {
		Set<OrderDetails> orderDetails = new HashSet<>();

		productDtos.stream().filter(Objects::nonNull).forEach(productDto -> {
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setQuantity(productDto.getQuantity());
			Product product = productMapper.productDtoToProduct(productService.getProductById(productDto.getId()));
			orderDetail.setProduct(product);
			orderDetail.setOrder(order);
			orderDetails.add(orderDetail);
		});

		return orderDetails;
	}

	public Customer customerDtoToCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setId(customerDto.getId());
		customer.setName(customerDto.getName());
		customer.setMobile(customerDto.getMobile());
		customer.setEmail(customerDto.getEmail());
		customer.setAddressLine1(customerDto.getAddressLine1());
		customer.setAddressLine2(customerDto.getAddressLine2());
		customer.setCity(customerDto.getCity());
		customer.setState(customerDto.getState());
		customer.setPincode(customerDto.getPincode());
		customer.setCountry(customerDto.getCountry());
		return customer;
	}

	public List<CustomerDto> customersToCustomerDtos(List<Customer> customers) {
		List<CustomerDto> customerDtos = new ArrayList<>();
		customers.stream().filter(Objects::nonNull).forEach(customer -> customerDtos.add(customerToCustomerDto(customer)));
		return customerDtos;
	}
	
	
	public CustomerDto customerToCustomerDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setName(customer.getName());
		customerDto.setMobile(customer.getMobile());
		customerDto.setAddressLine1(customer.getAddressLine1());
		customerDto.setAddressLine2(customer.getAddressLine2());
		customerDto.setCity(customer.getCity());
		customerDto.setState(customer.getState());
		customerDto.setCountry(customer.getCountry());
		customerDto.setPincode(customer.getPincode());
		customerDto.setEmail(customer.getEmail());
		return customerDto;
	}
}
