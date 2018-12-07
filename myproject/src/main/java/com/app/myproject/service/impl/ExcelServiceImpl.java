package com.app.myproject.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import com.app.myproject.constants.Constants;
import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.ExcelData;
import com.app.myproject.dto.StockDto;
import com.app.myproject.model.Customer;
import com.app.myproject.model.Order;
import com.app.myproject.repository.CustomerRepository;
import com.app.myproject.repository.OrderRepository;
import com.app.myproject.service.ExcelService;
import com.app.myproject.service.ProductService;
import com.app.myproject.util.CommonUtil;
import com.app.myproject.util.ExcelUtil;

@Service
public class ExcelServiceImpl implements ExcelService {
	
	@Inject
	private ExcelUtil excelUtil;
	
	@Inject
	private CommonUtil commonUtil;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private CustomerRepository customerRepository;
	
	@Inject
	private OrderRepository orderRepository;

	@Override
	public ByteArrayOutputStream getExcelAsBytes(String reportName) {
		ExcelData excelData = new ExcelData();
		if(Constants.STOCK.equals(reportName)){
			CustomPage<StockDto> page = productService.getStockDetails(null, null, true);
			List<StockDto> stockDtos = page.getContent();
			List<List<String>> rows = new ArrayList<>();
			for(StockDto stockDto : stockDtos){
				List<String> values = new ArrayList<>();
				values.add(stockDto.getCategoryName());
				values.add(stockDto.getBrandName());
				values.add(stockDto.getName());
				values.add(String.valueOf(stockDto.getTotalQty()));
				values.add(String.valueOf(stockDto.getOrderedQty()));
				values.add(String.valueOf(stockDto.getAvailableQty()));
				rows.add(values);
			}
			
			List<String> headers = new ArrayList<>();
			headers.add("Category Name");
			headers.add("Brand Name");
			headers.add("Product Name");
			headers.add("Total Qty");
			headers.add("Ordered Qty");
			headers.add("Available Qty");
			
			excelData.setSheetName(Constants.STOCK);
			excelData.setHeaders(headers);
			excelData.setRows(rows);
		} else if(Constants.CUSTOMERS.equals(reportName)){
			List<Customer> customers = customerRepository.findAll();
			
			List<List<String>> rows = new ArrayList<>();
			for(Customer customer : customers){
				List<String> values = new ArrayList<>();
				values.add(customer.getName());
				values.add(customer.getMobile());
				values.add(customer.getEmail());
				values.add(customer.getCity());
				values.add(customer.getState());
				rows.add(values);
			}
			
			excelData.setSheetName(Constants.CUSTOMERS);
			List<String> headers = new ArrayList<>();
			headers.add("Customer Name");
			headers.add("Mobile");
			headers.add("Email");
			headers.add("City");
			headers.add("State");
			
			excelData.setHeaders(headers);
			excelData.setRows(rows);
		} else if(Constants.ORDERS.equals(reportName)){
			List<Order> orders = orderRepository.findAll();
			List<List<String>> rows = new ArrayList<>();
			for(Order order : orders){
				List<String> values = new ArrayList<>();
				values.add(order.getOrderNumber());
				values.add(commonUtil.convertZonedDateTimeToString(order.getOrderDate(), Constants.DD_MM_YYYY));
				values.add(String.valueOf(order.getTotalAmount()));
				rows.add(values);
			}
			excelData.setSheetName(Constants.ORDERS);
			List<String> headers = new ArrayList<>();
			headers.add("Order Number");
			headers.add("Order Date");
			headers.add("Amount");
			excelData.setHeaders(headers);
			excelData.setRows(rows);
		}
		return excelUtil.getExcelAsBytes(excelData);
	}
	
	@Override
	public ByteArrayOutputStream getFileAsBytes(String fileType) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;
		try {
			if("csv".equalsIgnoreCase(fileType)) {
				is = ExcelServiceImpl.class.getClassLoader().getResourceAsStream("sample/products.csv");
			}  else if("xml".equalsIgnoreCase(fileType)) {
				is = ExcelServiceImpl.class.getClassLoader().getResourceAsStream("sample/products.xml");
			}
			IOUtils.copy(is, baos);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return baos;
	}
}