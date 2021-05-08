package com.restController.learning.RestController;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Contains;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restController.learning.RestController.Controller.ControllerAPI;
import com.restController.learning.RestController.Entity.User;
import com.restController.learning.RestController.Service.PostService;
import com.restController.learning.RestController.Service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(ControllerAPI.class)
public class ControllerApiTest {

	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private PostService postService;
	
	@InjectMocks
	private ControllerAPI ControllerAPI;
	
	@Test
	public void getAllUserTest() throws Exception {
		when(userService.getAllUser()).thenReturn(Arrays.asList(new User(1, "krishna", "I am awesome"), new User(2, "singh", "Always rock")));
		
		RequestBuilder rb = MockMvcRequestBuilders.get("/api/users").accept(MediaType.APPLICATION_JSON);
		mockMVC.perform(rb).andExpect(status().isOk()).andExpect(content().json("[{id:1},{id:2}]", false)).andReturn();
	}
	
	@Test
	public void getSingleUserTest() throws Exception {
		
		int id = 1;
		
		User user = new User();
		user.setId(1);
		user.setName("krishna");
		user.setDesc("I am awesome");
		
		when(userService.getUserByID(id)).thenReturn(Optional.of(user));
		
		RequestBuilder rb = MockMvcRequestBuilders.get("/api/users/1").accept(MediaType.APPLICATION_JSON);
		mockMVC.perform(rb).andExpect(status().isOk()).andExpect(content().json("{name: krishna}")).andReturn();
		
	}
	@Test
	public void getSingleUserTestError() throws Exception {
		
		User user = new User();
		user.setId(2);
		user.setName("krishna");
		user.setDesc("I am awesome");
		
		when(userService.getUserByID(2)).thenReturn(Optional.of(user));
		
		RequestBuilder rb = MockMvcRequestBuilders.get("/api/users/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMVC.perform(rb).andExpect(status().isNotFound()).andReturn();
		
		JSONAssert.assertNotEquals("{name: krishna}", result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		
		User user = new User();
		user.setId(2);
		user.setName("krishna");
		user.setDesc("I am awesome");
		
		when(userService.deleteUser(2)).thenReturn(Optional.of(user));
		
		RequestBuilder rb = MockMvcRequestBuilders.delete("/api/users/2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMVC.perform(rb).andExpect(status().isOk()).andReturn();
		String abc = result.getResponse().getContentAsString();
		System.out.println(abc);
		assertEquals("User is deleted Successfuly", abc);
		
	}
	@Test
	public void postUserTest() throws Exception {
		
		/*User user = new User();
		user.setId(2);
		user.setName("krishna");
		user.setDesc("I am awesome");
		
		when(userService.addUser(user)).thenReturn(user);
		*/
		User user = new User(100, "kaka", "My dream proj");
		when(userService.addUser(Mockito.any(User.class))).thenReturn(user);
		RequestBuilder rb = MockMvcRequestBuilders.post("/api/users").accept(MediaType.APPLICATION_JSON)
				.content("{\"id\": 100, \"name\": \"Lets party!!!!\", \"desc\": \"I am awesome\"}")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMVC.perform(rb).andExpect(status().is(201)).andReturn();

		
	}
}
