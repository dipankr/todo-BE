package com.dipankr.todoBE.controller;

import com.dipankr.todoBE.entity.Todo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

    @CrossOrigin (origins = {})
    @GetMapping(path = "/api/todolist", produces = "application/json")
    @ResponseBody
    public String getTodoList() {
        return getResponseJson(new Gson().toJson(todoList), null, "success");
    }

    @CrossOrigin (origins = {})
    @PostMapping(path = "/api/todolist", produces = "application/json")
    public ResponseEntity<?> addTodo(@RequestBody String todo) {
        Todo tempTodo;
        try {
            Type type = new TypeToken<Todo>() {
            }.getType();
            tempTodo = new Gson().fromJson(todo, type);

        } catch (Exception e) {
            log.error("error parsing post body", e);
            return new ResponseEntity<>(getResponseJson(null, true, "error parsing request body"), HttpStatus.BAD_REQUEST);
        }

        if (null == tempTodo || null == tempTodo.getTitle() || 0 == tempTodo.getTitle().length()) {
            return new ResponseEntity<>(getResponseJson(null, true, "title can not be empty"), HttpStatus.BAD_REQUEST);
        }

        todoList.add(new Todo(tempTodo.getTitle(), tempTodo.getDescription()));

        return new ResponseEntity<>(getResponseJson(null, null, "added todo item to the list"), HttpStatus.OK);
    }

    private String getResponseJson(String data, Boolean error, String message) {
        StringBuilder sb = new StringBuilder().append("{")
                .append("\"response\": ").append("{");

        if (data != null && !data.isEmpty()) {
            sb.append("\"data\":").append(data).append(",");
        }

        if (error != null) {
            sb.append("\"error\":").append(error).append(",");
        }

        sb.append("\"message\":");
        if (message != null && !message.isEmpty()) {
            sb.append("\"").append(message).append("\"");
        } else sb.append("\"\"");

        sb.append("}}");
        return sb.toString();
    }
}
