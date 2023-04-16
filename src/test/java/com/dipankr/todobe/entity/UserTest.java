package com.dipankr.todobe.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testGson() {
        User user = new User("Ram", "password");
        user.getTodoList().addAll(
            List.of(
                new Todo("123", "title", false),
                new Todo("234", "title1", false),
                new Todo("345", "title2", false)
            )
        );

        Gson gson = new Gson();
        String todoListJson = gson.toJson(user.getTodoList());
        System.out.println(todoListJson);

        Type type = new TypeToken<List<Todo>>() {
        }.getType();
        List<Todo> todoList = gson.fromJson(todoListJson, type);

        assertEquals(user.getTodoList(), todoList);
    }

}