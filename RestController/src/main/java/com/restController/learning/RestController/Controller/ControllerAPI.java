package com.restController.learning.RestController.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restController.learning.RestController.DAO.APIUserDAO;
import com.restController.learning.RestController.DAO.UserDAO;
import com.restController.learning.RestController.Entity.Post;
import com.restController.learning.RestController.Entity.User;
import com.restController.learning.RestController.Exception.UserNotFoundException;
import com.restController.learning.RestController.Service.PostService;
import com.restController.learning.RestController.Service.UserService;

@RestController
//@RequestMapping("/api")
public class ControllerAPI {

	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@GetMapping("/api/users")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@GetMapping("/api/users/{id}")
	public EntityModel<User> getSingleUser(@PathVariable int id){
		Optional<User> user = userService.getUserByID(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id: "+id);
		
		 EntityModel<User> entityModel = EntityModel.of(user.get());
		 WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUser());
		 entityModel.add(linkTo.withRel("all-users: "));
		    return entityModel;	
	}
	@PostMapping("/api/users")
	public ResponseEntity<Object> addUser(@RequestBody User user){
		User savedUser = userService.addUser(user);
		
		URI uri = ServletUriComponentsBuilder.
		fromCurrentRequest().
		path("/{id}").
		buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
		}
	@DeleteMapping("/api/users/{id}")
	public String deleteUser(@PathVariable int id){
		 userService.deleteUser(id).get();
		 return "User is deleted Successfuly";
		}
	@GetMapping("/api/users/{id}/post")
	public List<Post> getPostForUser(@PathVariable int id){
		Optional<User> user = userService.getUserByID(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id: "+id);
		
		return user.get().getPost();
	}
}
