package com.full.project.tdd.TodoTdd;


import com.full.project.tdd.TodoTdd.Entity.ToDoEntity;
import com.full.project.tdd.TodoTdd.Repository.ToDoRepository;
import com.full.project.tdd.TodoTdd.Service.ToDoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoApplicationServiceTest {

    final Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    public void findAll() throws Exception {
        log.info("Lodded properly without any error");
        ToDoEntity toDoEntity = new ToDoEntity(1,"bond james",false);
        toDoRepository.save(toDoEntity);
        ToDoService toDoService = new ToDoService(toDoRepository);

        ToDoEntity firstResult = toDoService.findAll().get(0);


        assertEquals(toDoEntity.getName(), firstResult.getName());
        assertEquals(toDoEntity.isValue(), firstResult.isValue());
        assertEquals(toDoEntity.getId(), firstResult.getId());

    }
}
