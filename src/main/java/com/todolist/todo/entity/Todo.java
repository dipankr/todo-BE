package com.todolist.todo.entity;

import com.todolist.todo.service.IDService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {
    private final String id;
    private String title;
    private String description;
    private Boolean completed;

    public Todo(String title, String description){
        this.id = String.valueOf(IDService.getInstance().getID());
        this.title = title;
        this.description = description;
        this.completed = Boolean.FALSE;
    }
}
