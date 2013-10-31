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
@Table(name = "USERS")
public class User implements Serializable {
	
	
	
	@Id
	@Column(name="Username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="Password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="BirthDay")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	//@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@DateTimeFormat(iso=ISO.DATE)
	public DateTime getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(DateTime birthDay) {
		this.birthDay = birthDay;
	}
	
	@Column(name="FirstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="LastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="Phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="StartDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	//@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@DateTimeFormat(iso=ISO.DATE)
	public DateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	
	@Basic(fetch=FetchType.LAZY)
	@Lob @Column(name="Photo")
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	@Column(name="Role")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Transient
	public String getBirthDateString(){
		String birthDateString ="";
		if(birthDay!=null){
			birthDateString=org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(birthDay);
		}
		return birthDateString;
	}
	
	@Transient
	public String getStartDateString(){
		String startDateString = "";
		if(startDate!=null){
			startDateString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(startDate);
		}
		return startDateString;
	}
	
	
	
	private String username;
	private String password;
	private DateTime birthDay;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String email;
	private DateTime startDate;
	private byte[] photo;
	private String role;
	

}





