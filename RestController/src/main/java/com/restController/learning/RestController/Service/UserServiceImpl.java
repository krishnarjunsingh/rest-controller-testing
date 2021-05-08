package com.restController.learning.RestController.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restController.learning.RestController.DAO.APIUserDAO;
import com.restController.learning.RestController.Entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	APIUserDAO userDOA;
	
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDOA.findAll();
	}

	public Optional<User> getUserByID(Integer id) {
		// TODO Auto-generated method stub
		return userDOA.findById(id);
	}

	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userDOA.save(user);
	}

	public Optional<User> deleteUser(Integer id) {
		// TODO Auto-generated method stub
		Optional<User> user = userDOA.findById(id);
		if(user.isPresent())
			userDOA.deleteById(id);
		return user;
	}

}
