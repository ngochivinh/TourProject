package com.software.tour.web.form;

import java.util.List;
import com.software.tour.domain.Hotel;

public class HotelGrid {
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<Hotel> getHotelData() {
		return hotelData;
	}
	public void setHotelData(List<Hotel> contactData) {
		this.hotelData = contactData;
	}
	private int totalPages;
	private int currentPage;
	private long totalRecords;
	private List<Hotel> hotelData;
}
