package com.restController.learning.RestController.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restController.learning.RestController.Entity.Post;

@Repository
public interface PostDAO extends JpaRepository<Post, Integer> {

}
