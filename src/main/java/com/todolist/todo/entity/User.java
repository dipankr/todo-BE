package com.todolist.todo.entity;

import com.todolist.todo.service.IDService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {
    private final String id;
    private String username;
    private String password;
    private List<Todo> todoList;

    public User(String username, String password){
        this.id = String.valueOf(IDService.getInstance().getID());
        this.username = username;
        this.password = password;
        this.todoList = new ArrayList<>();
    }
}
