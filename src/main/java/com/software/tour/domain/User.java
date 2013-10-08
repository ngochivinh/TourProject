package com.software.tour.domain;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Long id;
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="FIRST_NAME")
	public String getFirstname() {
		return firstname;
	}
	 
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@Column(name="LAST_NAME")
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	public DateTime getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}
	@Column(name="USER_NAME")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="PASS_WORD")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="ROLE")
	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	
	private String firstname;
	private String lastname;
	private DateTime birthDate;
	private String username;
	private String password;
	int role;
	
}
