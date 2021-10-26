package com.spring.demo.mvc.service;

import java.util.List;

import com.spring.demo.mvc.model.User;

public interface UserService {

	  void save(User user);
	   List<User> list();

}
