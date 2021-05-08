package com.full.project.tdd.TodoTdd.Service;

import com.full.project.tdd.TodoTdd.Entity.ToDoEntity;
import com.full.project.tdd.TodoTdd.Repository.ToDoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ToDoService {


    private ToDoRepository toDoRepository;


    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Transactional
    public List<ToDoEntity> findAll() {

        return toDoRepository.findAll();
    }
}
