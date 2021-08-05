package com.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public class UserDaoImp implements UserDao {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public UserDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public User getUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User WHERE email=:email");
		query.setParameter("email", email);
		query.setMaxResults(1);
		List<User> users = query.getResultList();
		if(users.isEmpty()||users==null) {
			return null;
		}
		return users.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> allUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM User");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.byId(User.class).load(id);
		session.delete(user);
	}

}
