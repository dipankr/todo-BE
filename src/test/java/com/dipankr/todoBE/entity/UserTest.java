package com.dipankr.todoBE.entity;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserTest {
    @Test
    void testGson() {
        User user = new User("Ram", "password");
        user.getTodoList().addAll(List.of(new Todo("title", "desc"), new Todo("title1", "desc1"),new Todo("title2", "desc2")));
		Gson gson = new Gson();
		System.out.println(gson.toJson(user.getTodoList()));
    }

}