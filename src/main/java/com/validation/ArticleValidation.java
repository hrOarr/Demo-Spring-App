package com.validation;

import org.springframework.ui.Model;

import com.model.Article;

public class ArticleValidation {
	public boolean validation(Article article, Model model) {
		String title = article.getTitle();
		String body = article.getBody();
		boolean error = false;
		
		String regex = "^[a-zA-Z\\s]*$";
		
		if(title.length()<5) {
			error = true;
			if(title.matches(regex))model.addAttribute("title_error", "Title is too small");
			else model.addAttribute("title_error", "Invalid title");
		}
		else if(title.length()>55) {
			error = true;
			if(title.matches(regex))model.addAttribute("title_error", "Title is too long");
			else model.addAttribute("title_error", "Invalid title");
		}
		
		if(body.length()<10) {
			error = true;
			model.addAttribute("body_error", "body is too small");
		}
		else if(body.length()>6000) {
			error = true;
			model.addAttribute("body_error", "body is too long");
		}
		
		return error;
	}
}
