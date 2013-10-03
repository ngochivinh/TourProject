package com.software.tour.repository;
import org.springframework.data.repository.CrudRepository;
import com.software.tour.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
}
