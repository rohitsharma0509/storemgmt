package com.app.myproject.util;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.app.myproject.dto.jaxb.ProductsType;

@Component
public class CommonUtil {
	
	@Inject
	private Unmarshaller unmarshaller;
	
	@Inject
	private Marshaller marshaller;
	
	@Inject
	private MessageSource messageSource;
	
	public String convertLocalDateTimeToString(LocalDateTime localDateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(formatter);
	}	
	
	public String convertZonedDateTimeToString(ZonedDateTime zonedDateTime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(convertZonedDateTimeToDate(zonedDateTime));
	}
	
	public Date convertZonedDateTimeToDate(ZonedDateTime zonedDateTime) {
		return Date.from(zonedDateTime.toInstant());
	}
	
	public Date convertStringToDate(String inputDate, String format){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public String formatDate(String date, String inputDateFormat, String outputDateFormat) {
		SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat);
		Date utilDate = convertStringToDate(date, inputDateFormat);
		return outputFormat.format(utilDate);
	}
	
	public String getPagging(String baseUrl, int currentPage, int totalPage, Map<String, String> parameters) {
		int begin = Math.max(1, currentPage - 5);
		int end = Math.min(begin + 10, totalPage);
		
		StringBuilder params = new StringBuilder("");
		if(null != parameters){
			for (Map.Entry<String, String> entry : parameters.entrySet()){
				if(!StringUtils.isEmpty(entry.getValue())){
					params.append(entry.getKey()+"="+entry.getValue()+"&");
				}
			}
		}
		
		StringBuilder pagging = new StringBuilder("<ul class=\"pagination pagination-sm justify-content-end\">");

		if(currentPage == 1){
			pagging.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">First</a></li>");
			pagging.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">Previous</a></li>");
		}else {
			pagging.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+baseUrl+"?page=1&"+params+"\">First</a></li>");
			pagging.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+baseUrl+"?page="+(currentPage-1)+"&"+params+"\">Previous</a></li>");
		}
		
		for(int i = begin; i<= end; i++){
			if(i==currentPage){
				pagging.append("<li class=\"page-item active\"><a class=\"page-link\" href=\""+baseUrl+"?page="+i+"&"+params+"\">"+i+"</a></li>");
			}else {
				pagging.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+baseUrl+"?page="+i+"&"+params+"\">"+i+"</a></li>");		
			}
		}
		
		if(currentPage == totalPage){
			pagging.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">Next</a></li>");
			pagging.append("<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">Last</a></li>");
		}else {
			pagging.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+baseUrl+"?page="+(currentPage + 1)+"&"+params+"\">Next</a></li>");
			pagging.append("<li class=\"page-item\"><a class=\"page-link\" href=\""+baseUrl+"?page="+totalPage+"&"+params+"\">Last</a></li>");
		}
		pagging.append("</ul>");
		return pagging.toString();
	}
	
	public boolean isDouble(String number) {
		try {
			Double.parseDouble(number);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public boolean isInteger(String number) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public Boolean isValid(String value){
		return !(StringUtils.isEmpty(value) || "-1".equals(value));
	}
	
	public String[] convertStringToArray(String str, String delimeter) {
		if(StringUtils.isEmpty(str)){
			return null;
		} else {
			return str.split(delimeter);
		}
	}
	
	public String convertProductsTypeToXml(ProductsType productsType) {
		StringWriter xml = null;

		try {
			xml = new StringWriter();
			marshaller.marshal(productsType, xml);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xml.toString();
	}

	public ProductsType convertXmlToProductsType(String response) throws JAXBException {
		return (ProductsType) unmarshaller.unmarshal(new ByteArrayInputStream(response.getBytes()));
	}
	
	public String getValue(String value) {
		return messageSource.getMessage(value, new Object[]{value},  LocaleContextHolder.getLocale());
	}
}
