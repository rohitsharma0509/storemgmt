package com.app.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.myproject.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByMobile(String mobile);
}
