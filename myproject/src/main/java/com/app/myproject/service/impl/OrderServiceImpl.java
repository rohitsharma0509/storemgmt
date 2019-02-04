package com.app.myproject.service.impl;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.myproject.dto.CustomPage;
import com.app.myproject.dto.CustomerDto;
import com.app.myproject.dto.OrderDto;
import com.app.myproject.dto.ProductDto;
import com.app.myproject.mapper.OrderMapper;
import com.app.myproject.model.Order;
import com.app.myproject.repository.OrderRepository;
import com.app.myproject.service.OrderService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class OrderServiceImpl implements OrderService {

	@Inject
	private OrderMapper orderMapper;

	@Inject
	private OrderRepository orderRepository;
	
	public OrderDto addOrder(java.util.List<ProductDto> productDtos, CustomerDto customerDto, Double totalPrice) {
		Order order = orderRepository.save(orderMapper.convertToOrder(productDtos, customerDto, totalPrice));
		return orderMapper.orderToOrderDto(order);
	}

	@Override
	public OrderDto getOrder(Long id) {
	    Optional<Order> optional = orderRepository.findById(id);
	    if(optional.isPresent()) {
	        return orderMapper.orderToOrderDto(optional.get());
	    } else {
	        return null;
	    }
	}

	@Override
	public Page<Order> getOrders(Pageable pageable) {
		PageRequest request = PageRequest.of(pageable.getPageNumber() - 1,
				pageable.getPageSize(), pageable.getSort());
		return orderRepository.findAll(request);
	}
	
	@Override
	public Long countByOrderDate(ZonedDateTime orderDate){
		return orderRepository.countByOrderDate(orderDate);
	}
	
	@Override
	public Long countByOrderDateGreaterThanEqual(ZonedDateTime orderDate){
		return orderRepository.countByOrderDateGreaterThanEqual(orderDate);
	}
	
	public ByteArrayOutputStream createOrderPdf(Long id){
		OrderDto orderDto = getOrder(id);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try  {
			Font normalFont = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
			Font boldFont = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
			
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, baos);
			document.open();
			
			Phrase customerPhrase = new Phrase("", normalFont);
			customerPhrase.add("Order Number: "+ orderDto.getOrderNumber());
			customerPhrase.add("\n");
			customerPhrase.add(orderDto.getCustomerDto().getName());
			customerPhrase.add("\n");
			customerPhrase.add(orderDto.getCustomerDto().getAddressLine1());
			customerPhrase.add("\n");
			customerPhrase.add(orderDto.getCustomerDto().getAddressLine2());
			customerPhrase.add("\n");
			customerPhrase.add(orderDto.getCustomerDto().getCity()+", "+orderDto.getCustomerDto().getState()+", "+orderDto.getCustomerDto().getPincode());
			customerPhrase.add("\n");
			customerPhrase.add("Email: "+orderDto.getCustomerDto().getEmail());
			customerPhrase.add("\n");
			customerPhrase.add("Mobile: "+orderDto.getCustomerDto().getMobile());

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			PdfPCell cell = new PdfPCell(customerPhrase);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			
			Phrase datePhrase = new Phrase(orderDto.getOrderDate(), normalFont);

			cell = new PdfPCell(datePhrase);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("", normalFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("", normalFont));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			document.add(table);
			
			table = new PdfPTable(6);
			table.setWidthPercentage(100);
			
			cell = new PdfPCell(new Phrase("#", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Item", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Description", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Unit Cost", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Qty", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Total", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBackgroundColor(new BaseColor(233, 236, 239));
			cell.setFixedHeight(30);
			table.addCell(cell);
			
			int count = 0;
			for(ProductDto productDto : orderDto.getProductDtos()) {
				cell = new PdfPCell(new Phrase(count+1+"",normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);				
				
				cell = new PdfPCell(new Phrase(productDto.getName(), normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(productDto.getCode(), normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(productDto.getPerProductPrice()+"", normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(productDto.getQuantity()+"", normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase((productDto.getPerProductPrice()*productDto.getQuantity())+"", normalFont));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setFixedHeight(30);
				table.addCell(cell);
			}
			
			cell = new PdfPCell(new Phrase("Subtotal", boldFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setFixedHeight(30);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(orderDto.getTotalAmount().toString(), normalFont));
            cell.setFixedHeight(30);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(5);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Tax", boldFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(30);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("0.0", normalFont));
            cell.setFixedHeight(30);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(5);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Total", boldFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(30);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(orderDto.getTotalAmount().toString(), normalFont));
            cell.setFixedHeight(30);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(5);
            table.addCell(cell);
			
			document.add(table);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baos;
	}
	
	@Override
	public CustomPage<OrderDto> searchOrders(Order order, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
				.withMatcher("orderNumber", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<Order> example = Example.of(order, matcher);
		PageRequest request = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
		Page<Order> page = orderRepository.findAll(example, request);
		CustomPage<OrderDto> customPage = new CustomPage<>();
		customPage.setContent(orderMapper.ordersToOrderDtos(page.getContent()));
		customPage.setPageNumber(page.getNumber());
		customPage.setSize(page.getSize());
		customPage.setTotalPages(page.getTotalPages());
		return customPage;
	}
}