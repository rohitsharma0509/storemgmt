package com.app.myproject.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.Constants;
import com.app.myproject.constants.RequestUrls;
import com.app.myproject.service.ExcelService;

@Controller
public class ExcelController {
	
	@Inject
	private ExcelService excelService;
	
	@GetMapping(value = RequestUrls.EXCEL)
	public void downloadExcel(HttpServletResponse response, @RequestParam(required=true) String reportName) throws IOException {
		response.setContentType("application/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\""+ reportName + ".xlsx\"");
		ByteArrayOutputStream baos = excelService.getExcelAsBytes(reportName);
	    response.setContentLength(baos.size());
	    baos.writeTo(response.getOutputStream());
	}
	
	@GetMapping(value = RequestUrls.SAMPLE)
	public void downloadSample(HttpServletResponse response, @RequestParam(required=true) String fileType) throws IOException {
		if(Constants.CSV.equalsIgnoreCase(fileType)) {
			response.setContentType("application/csv");
		} else if(Constants.XML.equalsIgnoreCase(fileType)) {
			response.setContentType("application/xml");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"sample."+fileType+"\"");
		ByteArrayOutputStream baos = excelService.getFileAsBytes(fileType);
	    response.setContentLength(baos.size());
	    baos.writeTo(response.getOutputStream());
	}
}