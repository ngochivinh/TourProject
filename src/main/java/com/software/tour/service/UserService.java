package com.software.tour.service;

import java.util.List;
import com.software.tour.domain.User;

public interface UserService {
	public List<User> findAll();
	public User findById(Long id);
	public User Save(User user);
}
