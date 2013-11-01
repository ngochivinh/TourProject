package com.software.tour.domain;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;


@Entity
@Table(name="RESTAURANT")
public class Restaurant implements Serializable {
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name = "Id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "Phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "Price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Column(name = "Status")
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Column(name = "Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	private int id;
	private String name;
	private String phone;
	private String address;
	private String email;
	private double price;
	private boolean status;
	private String description;
}
