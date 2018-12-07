package com.app.myproject.dto;

import java.util.List;

public class ExcelData {
	private String sheetName;
	private List<String> headers;
	private List<List<String>> rows;

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public List<List<String>> getRows() {
		return rows;
	}

	public void setRows(List<List<String>> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "ExcelData [sheetName=" + sheetName + ", headers=" + headers
				+ ", rows=" + rows + "]";
	}
}
