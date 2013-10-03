package com.software.tour.service;

import java.util.List;
import com.software.tour.domain.User;

public interface UserService {
	public List<User> findAll();
	//public User findByName(String name);
}
