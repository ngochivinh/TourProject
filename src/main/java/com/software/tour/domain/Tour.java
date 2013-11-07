package com.software.tour.domain;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.jadira.usertype.dateandtime.joda.PersistentDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="TOUR")
public class Tour implements Serializable {
	
	
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="Capacity")
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Column(name="Cus_num")
	public int getNumCustomer() {
		return numCustomer;
	}
	public void setNumCustomer(int numCustomer) {
		this.numCustomer = numCustomer;
	}
	
	@Column(name="Can_num")
	public int getCancelCustomer() {
		return cancelCustomer;
	}
	public void setCancelCustomer(int cancelCustomer) {
		this.cancelCustomer = cancelCustomer;
	}
	
	@Column(name="Departure")
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	
	@Column(name="StartDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso=ISO.DATE)
	public DateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	
	@Column(name="FinishDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso=ISO.DATE)
	public DateTime getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(DateTime finishDate) {
		this.finishDate = finishDate;
	}
	
	@Column(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="Price")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Basic(fetch=FetchType.LAZY)
	@Lob @Column(name="Photo")
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	@Column(name="Category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name="Guild")
	public String getGuild() {
		return guild;
	}
	public void setGuild(String guild) {
		this.guild = guild;
	}
	
	@Column(name="Status")
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	private Long id;
	private String name;
	private int capacity;
	private int numCustomer;
	private int cancelCustomer;
	private String departure;
	private DateTime startDate;
	private DateTime finishDate;
	private String description;
	private float price;
	private byte[] photo;
	private String category;
	private String guild;
	private boolean status;
}
