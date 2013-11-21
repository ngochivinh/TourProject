package com.software.tour.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.software.tour.domain.Tour;
import com.software.tour.service.TourService;
import com.software.tour.repository.TourRepository;
import com.software.tour.util.SearchDTO;
import com.software.tour.util.MyPageRequest;
import com.software.tour.domain.TourSpecifications;
import com.google.common.collect.Lists;

 


@Service("tourService")
@Repository
@Transactional
public class TourServiceImpl implements TourService {
	
	@Autowired
	private TourRepository tourRepository;
	
	@Transactional(readOnly=true)
	public List<Tour> findAll(){
		return Lists.newArrayList(tourRepository.findAll());
	}
	
	
	
	@Transactional(readOnly=true)
	public Tour findById(Long id){
		return tourRepository.findOne(id);
	}
	
	public Tour save(Tour tour) {
		return tourRepository.save(tour);
	}
	
	@Transactional(readOnly=true)
	public Page<Tour> findAllByPage(Pageable pageable){
		return tourRepository.findAll(pageable);
	}
	
	public void delete(Tour tour){
		tourRepository.delete(tour);
	}
	
	private Sort sortByLastNameAndFirstNameAsc() {
		return new Sort(new Sort.Order(Sort.Direction.ASC, "name"),	new Sort.Order(Sort.Direction.ASC, "phone"));
	}
	
	private Pageable buildPageSpecification(int pageIndex, int pageSize) {
		Sort sortSpec = sortByLastNameAndFirstNameAsc();
		return new PageRequest(pageIndex, pageSize, sortSpec);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Tour> search(SearchDTO dto) {
		Specification<Tour> hotelSpec = TourSpecifications.firstOrLastNameStartsWith(dto.getSearchTerm());
		Pageable pageSpecification = buildPageSpecification(dto.getPageIndex(), dto.getPageSize());
		Page<Tour> page = tourRepository.findAll(hotelSpec, pageSpecification);
		return page.getContent();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Tour> search(MyPageRequest myPageRequest) {
		Specification<Tour> hotelSpec = TourSpecifications.firstOrLastNameStartsWith(myPageRequest.getSearchTerm());
		Pageable pageable = new PageRequest(myPageRequest.getPageIndex(), myPageRequest.getPageSize(), myPageRequest.getSort());
		return tourRepository.findAll(hotelSpec, pageable);
	}
	
}
