package com.full.project.tdd.TodoTdd.Service;

import com.full.project.tdd.TodoTdd.Entity.ToDoEntity;
import com.full.project.tdd.TodoTdd.Repository.ToDoRepositorySessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoServiceSessionFactoryImpl implements ToDoServiceSessionFactory {

    @Autowired
    private ToDoRepositorySessionFactory toDoRepositorySessionFactory;

    @Override
    public List<ToDoEntity> findAll() {
        return toDoRepositorySessionFactory.findAll();
    }
}
