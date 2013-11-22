package com.software.tour.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Hotel.class)
public class Hotel_ {
	
	public static volatile SingularAttribute<Hotel, String> name;
	
	public static volatile SingularAttribute<Hotel, String>	phone;
	
	public static volatile SingularAttribute<Hotel, String>	address;
	
	public static volatile SingularAttribute<Hotel, String>	email;
}
