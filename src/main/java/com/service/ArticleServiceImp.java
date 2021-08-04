package com.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ArticleDao;
import com.dao.TagDao;
import com.dao.UserDao;
import com.model.Article;
import com.model.ArticleDTO;
import com.model.Tag;

@Service
public class ArticleServiceImp implements ArticleService {
	
	private ArticleDao articleDao;
	private UserDao userDao;
	private TagDao tagDao;
	
	@Autowired
	public ArticleServiceImp(ArticleDao articleDao, UserDao userDao, TagDao tagDao) {
		this.articleDao = articleDao;
		this.userDao = userDao;
		this.tagDao = tagDao;
	}
	
	@Override
	public void saveArticle(ArticleDTO articleDTO) {
		Article article = articleDTOtoArticle(articleDTO);
		articleDao.saveArticle(article);
	}

	@Override
	public List<Article> getArticleList() {
		return articleDao.getArticleList();
	}

	@Override
	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	@Override
	public void updateArticle(ArticleDTO articleDTO) {
		Article article = articleDTOtoArticle(articleDTO);
		articleDao.updateArticle(article);
	}
	
	@Override
	public List<Article> getArticlesByTagName(String name) {
		return articleDao.getArticlesByTagName(name);
	}
	
	public Article articleDTOtoArticle(ArticleDTO articleDTO) {
		Article article = new Article();
		// set id
		article.setId(articleDTO.getId());
		// set title
		article.setTitle(articleDTO.getTitle());
		// set tags
		String taglist = articleDTO.getTags();
		List<String> tags = Arrays.asList(taglist.split(","));
		for(String tg:tags) {
			String upper = tg.trim().toUpperCase();
			if(upper.isEmpty()||upper.isBlank())continue;
			Tag tag = tagDao.getTagByName(upper);
			if(tag == null) {
				tag = new Tag(upper);
			}
			article.getTags().add(tag);
		}
		// set body
		article.setBody(articleDTO.getBody());
		// set user
		article.setUser(userDao.getUserById(articleDTO.getUser_id()));
		return article;
	}
	
	@Override
	public ArticleDTO articleToArticleDTO(Article article) {
		ArticleDTO articleDTO = new ArticleDTO();
		// set id
		articleDTO.setId(article.getId());
		// set title
		articleDTO.setTitle(article.getTitle());
		// set tags
		List<Tag> tags = article.getTags();
		String str = "";
		if(!tags.isEmpty()) {
			for(Tag tag:tags) {
				str += tag.getName().toLowerCase();
				str += ",";
			}
		}
		articleDTO.setTags(str);
		// set body
		articleDTO.setBody(article.getBody());
		// set user
		articleDTO.setUser_id(article.getUser().getId());
		return articleDTO;
	}

	@Override
	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}


}
