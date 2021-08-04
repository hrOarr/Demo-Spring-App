package com.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.Article;
import com.model.Tag;

@Repository
public class ArticleDaoImp implements ArticleDao {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public ArticleDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public void saveArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		session.save(article);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Article> getArticleList() {
		Session session = sessionFactory.getCurrentSession();
//		CriteriaBuilder cb = session.getCriteriaBuilder();
//		CriteriaQuery<Article> cq = cb.createQuery(Article.class);
//		Root<Article> root = cq.from(Article.class);
//		cq.select(root);
//		
//		TypedQuery<Article> allQuery = session.createQuery(cq);
		Query query = session.createQuery("FROM Article");
		List<Article> articles = query.getResultList();
		return articles;
	}

	@Override
	@Transactional
	public Article getArticle(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Article.class, id);
	}

	@Override
	@Transactional
	public void updateArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		session.update(article);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Article> getArticlesByTagName(String name) {
		Session session = sessionFactory.getCurrentSession();
		
//		CriteriaBuilder cb = session.getCriteriaBuilder();
//		CriteriaQuery<Article> cq = cb.createQuery(Article.class);
//		Root<Article> articles = cq.from(Article.class);
//		Join<Article, Tag> tags = articles.join("tags", JoinType.INNER);
//		
//		Predicate[] predicates = new Predicate[1];
//		predicates[0] = cb.equal(tags.get("name"), name);
//		
//		cq.select(articles).where(predicates);
//		TypedQuery<Article> query = session.createQuery(cq);
		
		Query query = session.createQuery("SELECT a FROM Article a INNER JOIN a.tags t WHERE t.name=:name");
		query.setParameter("name", name);
		List<Article> article = query.getResultList();
		return article;
	}

	@Override
	@Transactional
	public void deleteArticle(int id) {
		Session session = sessionFactory.getCurrentSession();
		Article article = (Article) session.get(Article.class, id);
		session.delete(article);
	}

}
