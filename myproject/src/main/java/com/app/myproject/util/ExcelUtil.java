package com.app.myproject.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.app.myproject.dto.ExcelData;

@Component
public class ExcelUtil {
	public ByteArrayOutputStream getExcelAsBytes(ExcelData excelData) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet(excelData.getSheetName());

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.RED.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row row = sheet.createRow(0);
			Cell cell = null;

			for (int i = 0; i < excelData.getHeaders().size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(excelData.getHeaders().get(i));
				cell.setCellStyle(headerCellStyle);
			}

			for (int i = 0; i < excelData.getRows().size(); i++) {
				row = sheet.createRow(i + 1);
				List<String> values = excelData.getRows().get(i);
				for (int k = 0; k < values.size(); k++) {
					cell = row.createCell(k);
					cell.setCellValue(values.get(k));
					sheet.autoSizeColumn(k);
				}
			}
			workbook.write(baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos;
	}
}