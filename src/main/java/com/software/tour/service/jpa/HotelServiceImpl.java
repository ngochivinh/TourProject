package com.software.tour.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.software.tour.domain.Hotel;
import com.software.tour.domain.HotelSpecifications;
import com.software.tour.repository.HotelRepository;
import com.software.tour.service.HotelService;




import org.springframework.data.domain.Page; 
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Service("hotelService")
@Repository
@Transactional
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Hotel> findAll() {
		
		return Lists.newArrayList(hotelRepository.findAll());
	}

	@Override
	@Transactional(readOnly=true)
	public Hotel findById(Long id) {
		return hotelRepository.findOne(id);
	}

	@Override
	@Transactional
	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public void delete(Hotel hotel) {
		hotelRepository.delete(hotel);
	}
	
	@Override
	@Transactional(readOnly=true) 
	public Page<Hotel> findAllByPage(Pageable pageable) {
		return hotelRepository.findAll(pageable); 
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
	public List<Hotel> search(SearchDTO dto) {
		Specification<Hotel> hotelSpec = HotelSpecifications.firstOrLastNameStartsWith(dto.getSearchTerm());
		Pageable pageSpecification = buildPageSpecification(dto.getPageIndex(), dto.getPageSize());
		Page<Hotel> page = hotelRepository.findAll(hotelSpec, pageSpecification);
		return page.getContent();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Hotel> search(MyPageRequest myPageRequest) {
		Specification<Hotel> hotelSpec = HotelSpecifications.firstOrLastNameStartsWith(myPageRequest.getSearchTerm());
		Pageable pageable = new PageRequest(myPageRequest.getPageIndex(), myPageRequest.getPageSize(), myPageRequest.getSort());
		return hotelRepository.findAll(hotelSpec, pageable);
	}

	/*@Override
	public List<Hotel> findAllForPage(int pageIndex, int pageSize) {
		Pageable pageSpecification = buildPageSpecification(pageIndex, pageSize); 
		Page<Hotel> page = hotelRepository.findAll(pageSpecification);
		return page.getContent();
	}*/
}
