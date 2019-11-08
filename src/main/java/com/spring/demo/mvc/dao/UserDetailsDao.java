package com.spring.demo.mvc.dao;

import com.spring.demo.mvc.model.User;

public interface UserDetailsDao {

	User findUserByUsername(String username);
}
