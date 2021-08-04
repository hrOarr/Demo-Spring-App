package com.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.model.Tag;

@Repository
public class TagDaoImp implements TagDao {

	private SessionFactory sessionFactory;
	
	public TagDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public void saveTag(Tag tag) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tag);
	}

	@Override
	@Transactional
	public List<Tag> allTags() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Tag>cq = cb.createQuery(Tag.class);
		Root<Tag> root = cq.from(Tag.class);
		cq.select(root);
		
		TypedQuery<Tag> allQuery = session.createQuery(cq);
		return allQuery.getResultList();
	}

	@Override
	@Transactional
	public Tag getTagById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Tag.class, id);
	}
	
	@Override
	@Transactional
	public Tag getTagByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
		Root<Tag> root = cq.from(Tag.class);
		cq.select(root).where(cb.equal(root.get("name"), name));
		
		TypedQuery<Tag> query = session.createQuery(cq);
		List<Tag> tags = query.getResultList();
		if(tags == null || tags.isEmpty()) {
			return null;
		}
		return tags.get(0);
	}

	@Override
	@Transactional
	public void update(Tag tag) {
		Session session = sessionFactory.getCurrentSession();
		session.update(tag);
	}


}
