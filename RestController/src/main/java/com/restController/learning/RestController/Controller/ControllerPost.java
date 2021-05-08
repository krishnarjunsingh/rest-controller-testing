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
import com.restController.learning.RestController.Exception.PostNotFoundException;
import com.restController.learning.RestController.Exception.UserNotFoundException;
import com.restController.learning.RestController.Service.PostService;
import com.restController.learning.RestController.Service.UserService;

@RestController
//@RequestMapping("/api")
public class ControllerPost {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/api/post")
	public List<Post> getAllPost(){
		return postService.getAllPost();
	}
	
	@GetMapping("/api/post/{id}")
	public EntityModel<Post> getByID(@PathVariable int id){
		Optional<Post> postOptional = postService.getPostByID(id);
		if(!postOptional.isPresent())
			throw new PostNotFoundException("id: " + id);
		
		EntityModel<Post> entityModel = EntityModel.of(postOptional.get());
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllPost());
		entityModel.add(linkBuilder.withRel("all-Post: "));
		return entityModel;
		
	}
	
	@DeleteMapping("/api/post/{id}")
	public String deleteByID(@PathVariable int id){
		postService.deletePostByID(id).get();
		return "Post got deleted with ID: " + id;
	}
	
	@PostMapping("/api/user/{id}/post")
	public ResponseEntity<Object> addPostByID(@PathVariable int id, @RequestBody Post post){
		Optional<User> userID = userService.getUserByID(id);
		if(!userID.isPresent())
			throw new UserNotFoundException("id: " + id);
		User user = userID.get();
		post.setUser(user);
		Post addPost = postService.savePost(post);
		
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(addPost.getId()).toUri();
				return ResponseEntity.created(uri).build();
	}
}
