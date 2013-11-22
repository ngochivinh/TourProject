package com.software.tour.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Restaurant.class)
public class Restaurant_ {
	
	public static volatile SingularAttribute<Restaurant, String> name;
	
	public static volatile SingularAttribute<Restaurant, String>	phone;
	
	public static volatile SingularAttribute<Restaurant, String>	address;
	
	public static volatile SingularAttribute<Restaurant, String>	email;
}
