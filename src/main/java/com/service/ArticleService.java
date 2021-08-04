package com.service;

import java.util.List;

import com.model.Article;
import com.model.ArticleDTO;

public interface ArticleService {
	public void saveArticle(ArticleDTO articleDTO);
	public Article getArticle(int id);
	public List<Article> getArticleList();
	public void updateArticle(ArticleDTO articleDTO);
	public List<Article> getArticlesByTagName(String name);
	public void deleteArticle(int id);
	public ArticleDTO articleToArticleDTO(Article article);
}
