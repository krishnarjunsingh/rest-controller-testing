package com.restController.learning.RestController.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restController.learning.RestController.DAO.UserDAO;
import com.restController.learning.RestController.Entity.User;
import com.restController.learning.RestController.Exception.UserNotFoundException;

@RestController
public class Controller {

	@Autowired
	UserDAO userDAO;
	
	@GetMapping("/users")
	public List<User> getAllUser(){
		return userDAO.allUserList();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> getSingleUser(@PathVariable int id){
		User user = userDAO.userByID(id);
		if(user == null)
			throw new UserNotFoundException("id: "+id);
		
		 EntityModel<User> entityModel = EntityModel.of(user);
		 WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUser());
		 entityModel.add(linkTo.withRel("all-users"));
		    return entityModel;	
	}
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user){
		User savedUser = userDAO.saveUser(user);
		
		URI uri = ServletUriComponentsBuilder.
		fromCurrentRequest().
		path("/{id}").
		buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
		}
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id){
		User user = userDAO.deleteByID(id);
		if(user == null)
			throw new UserNotFoundException("id: "+id);
		return user;		
		}
}
