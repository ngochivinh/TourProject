package com.software.tour.web.form;

import java.util.List;
import com.software.tour.domain.Tour;

public class TourGrid {
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
	public List<Tour> getTourData() {
		return tourData;
	}
	public void setTourData(List<Tour> contactData) {
		this.tourData = contactData;
	}
	private int totalPages;
	private int currentPage;
	private long totalRecords;
	private List<Tour> tourData;
}
