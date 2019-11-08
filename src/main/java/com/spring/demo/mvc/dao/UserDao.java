package com.spring.demo.mvc.dao;

import java.util.List;

import com.spring.demo.mvc.model.User;

public interface UserDao {

	void save(User user);
	List<User> list();

}
