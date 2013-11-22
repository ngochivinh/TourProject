package com.software.tour.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class HotelSpecifications {
	
	public static Specification<Hotel> selectWithSearchTerm(final String searchTerm) {
		return new Specification<Hotel>() {
			//Creates the search criteria
			@Override
			public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				String likePattern = getLikePattern(searchTerm);
				return cb.or(
					cb.like(cb.lower(root.<String>get(Hotel_.name)), likePattern),
					cb.like(cb.lower(root.<String>get(Hotel_.phone)), likePattern),
					cb.like(cb.lower(root.<String>get(Hotel_.address)), likePattern),
					cb.like(cb.lower(root.<String>get(Hotel_.email)), likePattern)
					);
			}
			private String getLikePattern(final String searchTerm) {
				return "%" + searchTerm.toLowerCase() + "%";
			}
		};
	}
}
