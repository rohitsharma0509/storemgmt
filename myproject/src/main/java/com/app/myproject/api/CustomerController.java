package com.app.myproject.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.dto.CustomerDto;
import com.app.myproject.model.Customer;
import com.app.myproject.service.CustomerService;
import com.app.myproject.util.CommonUtil;

@Controller
public class CustomerController {
	@Inject
	private CustomerService customerService;

	@Inject
	private CommonUtil commonUtil;

	@GetMapping(value = RequestUrls.CUSTOMERS)
	public String getCustomers(Model model, @RequestParam(required = false) String name,
			@RequestParam(required = false) String city, @RequestParam(required = false) String mobile,
			@PageableDefault(page = 1, size = 10) Pageable pageable) {
		Customer customer = new Customer();
		customer.setName(name);
		customer.setCity(city);
		customer.setMobile(mobile);
		Page<Customer> page = customerService.searchCustomers(customer, pageable);

		Map<String, String> params = new HashMap<>();
		params.put("name", name);
		params.put("city", city);
		params.put("mobile", mobile);

		model.addAttribute("customer", customer);
		model.addAttribute("pagging",
				commonUtil.getPagging("customers", page.getNumber() + 1, page.getTotalPages(), params));
		model.addAttribute("page", page);
		return "customers";
	}

	@GetMapping(value = RequestUrls.CUSTOMERS + "/search")
	public List<CustomerDto> searchCustomer(@RequestParam(required = true) String mobile) {
		return customerService.searchCustomerByMobile(mobile);
	}
}
