package com.full.project.tdd.TodoTdd.Repository;

import com.full.project.tdd.TodoTdd.Entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("toDoRepository")
public interface ToDoRepository extends JpaRepository<ToDoEntity, Integer> {
}
