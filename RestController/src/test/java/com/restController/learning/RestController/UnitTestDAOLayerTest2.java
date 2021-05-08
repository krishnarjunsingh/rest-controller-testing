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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import com.restController.learning.RestController.Service.UserServiceImpl;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitTestDAOLayerTest2 {
	
	@Autowired
	private PostDAO postDAO;
	
	@Test
	public void reteriveAllDataTest() throws Exception {
		
		List<Post> postList = postDAO.findAll();
		
		assertEquals(4, postList.size());
		
		assertEquals("Lets party", postList.get(0).getDescription());
	}
}
