package com.dipankr.todobe.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class User {

    private final String id;
    private final String username;
    private final String password;
    private final List<Todo> todoList;

    public User(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.todoList = new ArrayList<>();
    }
}
