package com.software.tour.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Tour.class)
public class Tour_ {
public static volatile SingularAttribute<Tour, String> name;
	
	public static volatile SingularAttribute<Tour, String>	phone;
}
