package com.app.myproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.myproject.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByMobileContaining(String mobile);
}
