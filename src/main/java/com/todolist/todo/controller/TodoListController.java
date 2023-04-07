package com.todolist.todo.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.todolist.todo.entity.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

@RestController
@Slf4j
public class TodoListController {
    ArrayList<Todo> todoList = new ArrayList<>();

    @GetMapping(path = "/api/todolist", produces = "application/json")
    @ResponseBody
    public String getTodoList() {
        return new Gson().toJson(todoList);
    }

    @PostMapping(path = "/api/todolist", produces = "application/json")
    public ResponseEntity<?> addTodo(@RequestBody String todo) {
        Todo tempTodo;
        try {
            Type type = new TypeToken<Todo>() {
            }.getType();
            tempTodo = new Gson().fromJson(todo, type);

        } catch (Exception e) {
            log.error("error parsing post body", e);
            return new ResponseEntity<>("{error: error parsing request body}," + todo,HttpStatus.BAD_REQUEST);
        }

        if(null == tempTodo || null == tempTodo.getTitle() || 0 == tempTodo.getTitle().length()){
            return new ResponseEntity<>("{\"error\": \"title can not be empty\"}",HttpStatus.BAD_REQUEST);
        }

        todoList.add(new Todo(tempTodo.getTitle(), tempTodo.getDescription()));

        return new ResponseEntity<>("added todo item to the list", HttpStatus.OK);
    }
}
