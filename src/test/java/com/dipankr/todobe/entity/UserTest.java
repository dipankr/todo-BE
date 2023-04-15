package com.dipankr.todobe.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    @Test
    void testGson() {
        User user = new User("Ram", "password");
        user.getTodoList().addAll(List.of(new Todo("title", "desc"), new Todo("title1", "desc1"),new Todo("title2", "desc2")));

        Gson gson = new Gson();
        String todoListJson = gson.toJson(user.getTodoList());
        System.out.println(todoListJson);

        Type type = new TypeToken<List<Todo>>() {}.getType();
        List<Todo> todoList = gson.fromJson(todoListJson, type);

        assertEquals(user.getTodoList(), todoList);
    }

}