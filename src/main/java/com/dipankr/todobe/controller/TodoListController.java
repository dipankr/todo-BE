package com.dipankr.todobe.controller;

import static com.dipankr.todobe.service.ResponseService.getResponseJson;

import com.dipankr.todobe.entity.Todo;
import com.dipankr.todobe.service.TodoListService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TodoListController {

    ArrayList<Todo> todoList = new ArrayList<>();

    @CrossOrigin
    @GetMapping(path = "/api/todolist", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getTodoList() {
        return getResponseJson(new Gson().toJson(todoList), null, "success", HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(path = "/api/todolist", produces = "application/json")
    public ResponseEntity<?> addTodo(@NonNull @RequestBody String todo) {
        Todo tempTodo;
        try {
            Type type = new TypeToken<Todo>() {
            }.getType();
            tempTodo = new Gson().fromJson(todo, type);

        } catch (Exception e) {
            log.error("error parsing post body", e);
            return getResponseJson(new Gson().toJson(todoList), true, "error parsing request body", HttpStatus.BAD_REQUEST);
        }

        if (null == tempTodo || null == tempTodo.getTitle() || 0 == tempTodo.getTitle().length()) {
            return getResponseJson(new Gson().toJson(todoList), true, "title can not be empty", HttpStatus.BAD_REQUEST);
        }

        todoList.add(tempTodo);

        return getResponseJson(new Gson().toJson(todoList), null, "added todo item to the list", HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(path = "/api/todolist/{todoId}", produces = "application/json")
    public ResponseEntity<?> deleteTodo(@PathVariable String todoId) {
        boolean success = TodoListService.deleteTodoById(todoList, todoId);

        if (success) {
            return getResponseJson(new Gson().toJson(todoList), null, "deleted todo item from the list", HttpStatus.OK);
        }
        return getResponseJson(new Gson().toJson(todoList), true, "could not delete item", HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @PutMapping(path = "/api/todolist", produces = "application/json")
    public ResponseEntity<?> updateTodo(@NonNull @RequestBody String todo) {
        Todo tempTodo;
        try {
            Type type = new TypeToken<Todo>() {
            }.getType();
            tempTodo = new Gson().fromJson(todo, type);

        } catch (Exception e) {
            log.error("error parsing post body", e);
            return getResponseJson(null, true, "error parsing request body", HttpStatus.OK);
        }

        boolean success = TodoListService.updateTodoById(todoList, tempTodo.getId(), tempTodo);

        if (success) {
            return getResponseJson(new Gson().toJson(todoList), null, "updated todo item in the list", HttpStatus.OK);
        }
        return getResponseJson(null, true, "could not update item", HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(path = "/api/todolist", produces = "application/json")
    public ResponseEntity<?> deleteCompletedTodo() {
        TodoListService.deleteCompletedTodo(todoList);

        return getResponseJson(new Gson().toJson(todoList), null, "deleted completed todo item from the list", HttpStatus.OK);

    }
}
