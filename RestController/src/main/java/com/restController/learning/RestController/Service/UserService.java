package com.restController.learning.RestController.Service;

import java.util.List;
import java.util.Optional;

import com.restController.learning.RestController.Entity.User;

public interface UserService {

	List<User> getAllUser();
	Optional<User> getUserByID(Integer id);
	User addUser(User user);
	Optional<User> deleteUser(Integer id);
}
