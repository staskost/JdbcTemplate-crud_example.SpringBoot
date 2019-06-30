package com.staskost.jdbc.dao;

import java.util.List;

import com.staskost.jdbc.model.User;

public interface UserDao {

	public void createUser(User user);

	public User getUserById(int id);

	public void updateUser(User user);

	public void deleteUser(int id);

	public List<User> getAllUsers();

}
