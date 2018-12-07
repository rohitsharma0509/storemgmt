package com.app.myproject.dto;

import java.util.List;

public class CustomPage<T> {
	private Integer pageNumber;
	private List<T> content;
	private Integer size;
	private Integer totalPages;
	private Integer totalRecords;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	@Override
	public String toString() {
		return "CustomPage [pageNumber=" + pageNumber + ", content=" + content
				+ ", size=" + size + ", totalPages=" + totalPages
				+ ", totalRecords=" + totalRecords + "]";
	}
}
