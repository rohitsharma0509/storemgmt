package com.app.myproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.myproject.dto.CustomerDto;
import com.app.myproject.model.Customer;

public interface CustomerService {

	Page<Customer> getCustomers(Pageable pageable);

	Page<Customer> searchCustomers(Customer customer, Pageable pageable);

	List<CustomerDto> searchCustomerByMobile(String mobile);
}
