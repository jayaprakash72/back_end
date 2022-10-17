package com.pfms.Personal_Finance_Management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long user_id;
	private String userName;
	private String password;
	private String email;
	

	public User(long user_id, String username, String password, String email) {
		super();
		this.user_id = user_id;
		this.userName = username;
		this.password = password;
		this.email = email;
	}
	

	
	public User() {
		
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
