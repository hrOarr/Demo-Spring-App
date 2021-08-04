package com.dao;

import java.util.List;

import com.model.User;

public interface UserDao {
	public void saveUser(User user);
	public User getUserById(int id);
	public User getUserByEmail(String email);
	public List<User> allUsers();
	public void updateUser(User user);
	public void deleteUser(int id);
}