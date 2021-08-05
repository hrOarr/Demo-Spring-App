package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "articles")
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "ArticleModel")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title", nullable = false, length = 255)
	private String title;
	
	@Column(name = "body", nullable = false, length = 6000)
	private String body;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "article_tag", joinColumns = {@JoinColumn(name="article_id")},
	inverseJoinColumns = {@JoinColumn(name="tag_id")})
	private List<Tag> tags = new ArrayList<Tag>();

	public Article() {
	}

	public Article(int id, String title, String body, User user, List<Tag> tags) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.user = user;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}