package com.staskost.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.staskost.jdbc.dao.UserDao;
import com.staskost.jdbc.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserDao userDao;

	@GetMapping
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		User user = userDao.getUserById(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
		}
		return user;
	}

	@PostMapping
	public void createUser(@RequestBody User user) {
		userDao.createUser(user);
	}

	@PutMapping
	public void updateUser(@RequestBody User user) {
		userDao.updateUser(user);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void deleteUser(@PathVariable int id) {
		User user = userDao.getUserById(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
		}
		userDao.deleteUser(id);
	}

}
