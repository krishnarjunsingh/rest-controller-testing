package com.restController.learning.RestController;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.restController.learning.RestController.Entity.Post;
import com.restController.learning.RestController.Service.PostService;


@RunWith(SpringRunner.class)
@WebMvcTest(UnitTestingLearnController.class)
public class UnitTestControllerTest {
	
	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private PostService postService;
	
	@Test
	public void basicFlowTest() throws Exception {
		
		RequestBuilder rb = MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMVC.perform(rb).andReturn();
		
		assertEquals("Hello World", result.getResponse().getContentAsString());
	}
	
	@Test
	public void basicFlowTwoTest() throws Exception {
		
		RequestBuilder rb = MockMvcRequestBuilders.get("/post").accept(MediaType.APPLICATION_JSON);
		
		//MvcResult result = mockMVC.perform(rb).andExpect(status().is(200)).andReturn();  or we can use ok status as well
		
		//andExpect(content().json("{id: 1}", false)) --> check only if some is return || andExpect(content().json("{id: 1}", true)) --> exact match of fields no req space
		// andExpect(content().string("{id: 1}")) need letter by letter exact match
		mockMVC.perform(rb).andExpect(status().isOk()).andExpect(content().json("{id: 1}", false)).andReturn();
		
	}

	@Test
	public void basicFlowThree() throws Exception {
		when(postService.addDummyPost()).thenReturn(new Post(2, "BOnd"));
		RequestBuilder rb = MockMvcRequestBuilders.get("/dep/post").accept(MediaType.APPLICATION_JSON);
		
		mockMVC.perform(rb).andExpect(status().isOk()).andExpect(content().json("{id:2}", false)).andReturn();
		
	}
	@Test
	public void basicFlowFour() throws Exception{
		when(postService.getAllPost()).thenReturn(Arrays.asList(new Post(1, "I am Bond"), new Post(2, "I am best")));
		RequestBuilder rb = MockMvcRequestBuilders.get("/all/post").accept(MediaType.APPLICATION_JSON);
		
		mockMVC.perform(rb).andExpect(status().isOk()).andExpect(content().json("[{},{}]")).andReturn();
	}
}
