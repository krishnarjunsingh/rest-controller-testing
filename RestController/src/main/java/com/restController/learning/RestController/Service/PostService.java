package com.restController.learning.RestController.Service;

import java.util.List;
import java.util.Optional;

import com.restController.learning.RestController.Entity.Post;

public interface PostService {
	
	List<Post> getAllPost();
	Optional<Post> getPostByID(Integer id);
	Optional<Post> deletePostByID(Integer id);
	Post savePost(Post post);
	Post addDummyPost();

}
