package com.full.project.tdd.TodoTdd;


import com.full.project.tdd.TodoTdd.Controller.ToDoController;
import com.full.project.tdd.TodoTdd.Entity.ToDoEntity;
import com.full.project.tdd.TodoTdd.Service.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ToDoController.class)
public class ToDoApplicationControllerTest {

    final Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @Test
    public void getAllTodo() throws Exception {
        log.info("Lodded properly without any error");
        List<ToDoEntity> listToDo = new ArrayList<>();
        listToDo.add(new ToDoEntity(1, "Hello how are you", true));
        listToDo.add(new ToDoEntity(2, "Have to work hard", false));

        Mockito.when(toDoService.findAll()).thenReturn(listToDo);


        mockMvc.perform(MockMvcRequestBuilders.get("/todos").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[{id:1},{name:\"Have to work hard\"}]", false))
                .andDo(print());

             //  .andExpect(Jackson2ObjectMapperBuilder.json("$", Matchers.hasSize(2))).andDo(print());
    }
}
