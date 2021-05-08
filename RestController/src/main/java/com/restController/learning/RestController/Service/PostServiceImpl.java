package com.restController.learning.RestController.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restController.learning.RestController.DAO.PostDAO;
import com.restController.learning.RestController.Entity.Post;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostDAO postDoa;

	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return postDoa.findAll();
	}

	public Optional<Post> getPostByID(Integer id) {
		// TODO Auto-generated method stub
		return postDoa.findById(id);
	}

	public Optional<Post> deletePostByID(Integer id) {
		// TODO Auto-generated method stub
		Optional<Post> post = postDoa.findById(id);
		if(post.isPresent())
			postDoa.deleteById(id);
		return post;
	}

	public Post savePost(Post post) {
		// TODO Auto-generated method stub
		return postDoa.save(post);
	}

	public Post addDummyPost() {
		// TODO Auto-generated method stub
		return new Post(1, "Me star hu");
	}

}
