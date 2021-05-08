package com.restController.learning.RestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restController.learning.RestController.Controller.UnitTestingLearnController;
import com.restController.learning.RestController.DAO.PostDAO;
import com.restController.learning.RestController.Entity.Post;
import com.restController.learning.RestController.Service.PostService;
import com.restController.learning.RestController.Service.PostServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UnitTestForAll {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void reteriveAllItemTest() throws Exception {
		
		String response = this.testRestTemplate.getForObject("/all/post", String.class);
		
		JSONAssert.assertEquals("[{id:1},{id:2},{id:3},{id:4}]", response, false);
	}
}
