package com.restController.learning.RestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restController.learning.RestController.Controller.UnitTestingLearnController;
import com.restController.learning.RestController.DAO.APIUserDAO;
import com.restController.learning.RestController.DAO.PostDAO;
import com.restController.learning.RestController.DAO.UserDAO;
import com.restController.learning.RestController.Entity.Post;
import com.restController.learning.RestController.Entity.User;
import com.restController.learning.RestController.Service.PostService;
import com.restController.learning.RestController.Service.PostServiceImpl;
import com.restController.learning.RestController.Service.UserServiceImpl;


@RunWith(SpringRunner.class)
public class UnitTestServiceLayerTest {
	
	@InjectMocks
	private PostServiceImpl postServiceImpl;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private PostDAO postDoa;
	
	@Mock
	private APIUserDAO aPIUserDAO;
	
	@Test
	public void reteriveAllItemTest() throws Exception {
		
		when(postDoa.findAll()).thenReturn(Arrays.asList(new Post(1, "I am awesome"), new Post(2, "I am awesome"), new Post(3, "I am awesome")));
		
		List<Post> postList =  postServiceImpl.getAllPost();
		
		assertEquals(1, postList.get(0).getId());
		assertEquals("I am awesome", postList.get(1).getDescription());
	}
	
	@Test
	public void reteriveItemByID() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setName("krishna");
		user.setDesc("I am awesome");
		
		when(aPIUserDAO.findById(1)).thenReturn(Optional.of(user));
		
		Optional<User> userDet =  userServiceImpl.getUserByID(1);
		
		assertEquals("krishna", userDet.get().getName());
		}
}
