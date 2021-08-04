package com.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ArticleDTO {
	private int id;

	@NotEmpty(message = "Title can not be Empty")
	@Size(min = 5, max = 55, message = "Title must be between 5 and 55 characters")
	private String title;

	@NotEmpty(message = "Body can not be Empty")
	@Size(min = 15, message = "Body is too short")
	private String body;

	private String username;
	
	@Positive(message = "User is not selected")
	private int user_id;
	
	@NotEmpty(message = "Tags need to be included")
	private String tags;

	public ArticleDTO() {
	}

	public ArticleDTO(int id, String title, String body, String username, int user_id, String tags) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.username = username;
		this.user_id = user_id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "ArticleDTO [id=" + id + ", title=" + title + ", body=" + body + ", username=" + username + ", user_id="
				+ user_id + ", tags=" + tags + "]";
	}
	
}
