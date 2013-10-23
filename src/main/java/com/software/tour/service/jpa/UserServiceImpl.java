package com.software.tour.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.software.tour.domain.User;
import com.software.tour.repository.UserRepository;
import com.software.tour.service.UserService;
import com.google.common.collect.Lists;

@Service("userService")
@Repository
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly=true)
	public List<User> findAll(){
		return Lists.newArrayList(userRepository.findAll());
	}
	

	@Transactional(readOnly=true)
	public User findById(Long id){
		return userRepository.findOne(id);
	}
	
	public User save(User user){
		return userRepository.save(user);
	}
	

}
