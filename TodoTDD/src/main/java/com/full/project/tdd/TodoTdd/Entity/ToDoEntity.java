package com.full.project.tdd.TodoTdd.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ToDoEntity {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private boolean value;

    public ToDoEntity() {}

    public ToDoEntity(int id, String name, boolean value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public ToDoEntity(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
