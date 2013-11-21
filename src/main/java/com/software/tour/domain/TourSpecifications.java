package com.software.tour.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
public class TourSpecifications {
	public static Specification<Tour> firstOrLastNameStartsWith(final String searchTerm) {
		return new Specification<Tour>() {
			//Creates the search criteria
			@Override
			public Predicate toPredicate(Root<Tour> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				String likePattern = getLikePattern(searchTerm);
				return cb.or(
					//First name starts with given search term
					cb.like(cb.lower(root.<String>get(Tour_.name)), 
					likePattern),
					//Last name starts with the given search term
					cb.like(cb.lower(root.<String>get(Tour_.phone)), 
					likePattern)
					);
			}
			private String getLikePattern(final String searchTerm) {
				return searchTerm.toLowerCase() + "%";
			}
		};
	}
}
