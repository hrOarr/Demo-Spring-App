package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.model.User;

@Service
public class UserServiceImp implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserServiceImp(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}
	
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public List<User> allUsers() {
		return userDao.allUsers();
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

}
