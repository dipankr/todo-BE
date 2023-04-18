package com.dipankr.todobe.entity;


import com.dipankr.todobe.service.IDService;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class User {
    private final String id;
    private String username;
    private String password;
    private List<Todo> todoList;

    public User(String username, String password) {
        this.id = String.valueOf(IDService.getInstance().getID());
        this.username = username;
        this.password = password;
        this.todoList = new ArrayList<>();
    }
}
