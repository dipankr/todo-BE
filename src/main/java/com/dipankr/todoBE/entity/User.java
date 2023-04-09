package com.dipankr.todoBE.entity;


import com.dipankr.todoBE.service.IDService;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    public boolean equalValues(User user) {
        return this.username.equals(user.getUsername()) &&
                this.password.equals(user.getPassword()) &&
                isEqualValueTodoList(user.getTodoList());
    }

    public boolean isEqualValueTodoList(List<Todo> todoList1) {
        for (int i = 0; i < todoList.size(); i++) {
            if (!todoList.get(i).equalsValue(todoList1.get(i))) return false;
        }
        return true;
    }
}
