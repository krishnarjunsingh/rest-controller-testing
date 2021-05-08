package com.restController.learning.RestController.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restController.learning.RestController.Entity.User;

@Repository
public interface APIUserDAO extends JpaRepository<User, Integer> {

}
