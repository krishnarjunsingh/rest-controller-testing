package com.full.project.tdd.TodoTdd.Controller;

import com.full.project.tdd.TodoTdd.Entity.ToDoEntity;
import com.full.project.tdd.TodoTdd.Service.ToDoService;
import com.full.project.tdd.TodoTdd.Service.ToDoServiceSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoController {

   @Autowired
    private ToDoService toDoService;

    @Autowired
    private ToDoServiceSessionFactory toDoServiceSessionFactory;

    @GetMapping("/todos")
    ResponseEntity<List<ToDoEntity>> getAllToDo(){

        return new ResponseEntity<>(toDoService.findAll(), HttpStatus.OK);
    }
        @GetMapping("/transmissions")
    ResponseEntity<List<ToDoEntity>> getAllToDoSessionService(){

        return new ResponseEntity<>(toDoServiceSessionFactory.findAll(), HttpStatus.OK);
    }
}
