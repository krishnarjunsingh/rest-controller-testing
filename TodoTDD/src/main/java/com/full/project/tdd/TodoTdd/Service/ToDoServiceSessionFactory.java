package com.full.project.tdd.TodoTdd.Service;

import com.full.project.tdd.TodoTdd.Entity.ToDoEntity;

import java.util.List;

public interface ToDoServiceSessionFactory {

    public List<ToDoEntity> findAll();
}
