package com.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginDTO {
	private int id;
	
	@NotEmpty(message = "Email can not be Empty")
	@Email(message = "Invalid Email")
	private String email;
	
	@NotEmpty(message = "Password can not be Empty")
	@Size(min = 5, max = 55, message = "Password must be between 5 and 55 characters")
	private String password;
	
	public LoginDTO() {}
	public LoginDTO(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	
}