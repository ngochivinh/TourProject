package com.software.tour.service.jpa;

import org.springframework.data.domain.Sort;

public class MyPageRequest {
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public MyPageRequest(int page, int rows, String search, Sort sort){
		this.pageIndex = page;
		this.pageSize = rows;
		this.sort = sort;
		this.searchTerm = search;
	}
	public MyPageRequest(int page, int rows, String search){
		this.pageIndex = page;
		this.pageSize = rows;
		this.searchTerm = search;
	}
	private int pageIndex;
	private int pageSize;//rows
	private String searchTerm;
	private Sort sort;
}
