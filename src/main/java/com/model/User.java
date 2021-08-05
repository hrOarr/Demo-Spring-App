package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"articles"})
@ApiModel(value = "UserModel")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotEmpty(message = "Name can not be Empty")
	@Size(min = 5, max = 55, message = "Name must be between 5 and 55 characters")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotEmpty(message = "Email can not be Empty")
	@Email(message = "Invalid Email")
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotEmpty(message = "Password can not be Empty")
	@Size(min = 5, max = 55, message = "Password must be between 5 and 55 characters")
	@Column(name = "password", nullable = false)
	private String password;
	
	@ApiModelProperty(hidden = true)
	@OneToMany(mappedBy = "user")
	private List<Article> articles = new ArrayList<Article>();
	
	public User() {}
	public User(int id, String name, String email, String password, List<Article> articles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.articles = articles;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
}