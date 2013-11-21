package com.software.tour.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class HotelSpecifications {
	
	public static Specification<Hotel> firstOrLastNameStartsWith(final String searchTerm) {
		return new Specification<Hotel>() {
			//Creates the search criteria
			@Override
			public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				String likePattern = getLikePattern(searchTerm);
				return cb.or(
					//First name starts with given search term
					cb.like(cb.lower(root.<String>get(Hotel_.name)), 
					likePattern),
					//Last name starts with the given search term
					cb.like(cb.lower(root.<String>get(Hotel_.phone)), 
					likePattern)
					);
			}
			private String getLikePattern(final String searchTerm) {
				return searchTerm.toLowerCase() + "%";
			}
		};
	}
}
