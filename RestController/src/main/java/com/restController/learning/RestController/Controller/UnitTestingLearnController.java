package com.restController.learning.RestController.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restController.learning.RestController.Entity.Post;
import com.restController.learning.RestController.Service.PostService;

@RestController
public class UnitTestingLearnController {
	
	@Autowired
	PostService postService;
	
	/*@GetMapping("/hello")
	public String basicFlow() {
		return "Hello World";
	}
	
	@GetMapping("/post")
	public Post basicFlowTwo() {
		return new Post(1, "How are you doing");
	}
	
	@GetMapping("/dep/post")
	public Post basicFlowThree() {
		return postService.addDummyPost();
	}*/
	@GetMapping("/all/post")
	public List<Post> basicFlowFour() {
		return postService.getAllPost();
	}

}
