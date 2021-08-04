package com.dao;

import java.util.List;

import com.model.Article;

public interface ArticleDao {
	public void saveArticle(Article article);
	public Article getArticle(int id);
	public List<Article> getArticleList();
	public void updateArticle(Article article);
	public void deleteArticle(int id);
	public List<Article> getArticlesByTagName(String name);
}