package com.full.project.tdd.TodoTdd.Repository;

import com.full.project.tdd.TodoTdd.Entity.ToDoEntity;

import java.util.List;

public interface ToDoRepositorySessionFactory {

    public List<ToDoEntity> findAll();
}
