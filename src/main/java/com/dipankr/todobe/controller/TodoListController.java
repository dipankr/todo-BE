package com.dipankr.todobe.controller;

import static com.dipankr.todobe.service.AsyncThreadService.async;
import static com.dipankr.todobe.wrapper.ResponseWrapper.getResponseJson;

import com.dipankr.todobe.entity.Todo;
import com.dipankr.todobe.service.TodoListService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin
@RestController
@Slf4j
public class TodoListController {

    private final List<Todo> todoList;
    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        todoList = todoListService.getAllTodo();
        this.todoListService = todoListService;
    }

    @GetMapping(path = "/api/todolist", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getTodoList() {
        return getResponseJson(new Gson().toJson(todoList), null, "success", HttpStatus.OK);
    }

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

        if (null == tempTodo) {
            return getResponseJson(new Gson().toJson(todoList), true, "invalid data", HttpStatus.NOT_ACCEPTABLE);
        }
        if (null == tempTodo.getId()) {
            return getResponseJson(new Gson().toJson(todoList), true, "ID not valid", HttpStatus.NOT_ACCEPTABLE);
        }
        if (null == tempTodo.getTitle() || 0 == tempTodo.getTitle().length()) {
            return getResponseJson(new Gson().toJson(todoList), true, "title can not be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        todoList.add(tempTodo);
        async(() -> todoListService.addTodo(tempTodo));

        return getResponseJson(new Gson().toJson(todoList), null, "added todo item to the list", HttpStatus.OK);
    }

    @DeleteMapping(path = "/api/todolist/{todoId}", produces = "application/json")
    public ResponseEntity<?> deleteTodo(@PathVariable String todoId) {
        todoList.removeIf(todo -> todo.getId().equals(todoId));
        async(() -> todoListService.deleteTodoById(todoId));

        return getResponseJson(new Gson().toJson(todoList), null, "deleted todo item from the list", HttpStatus.OK);
    }

    @PutMapping(path = "/api/todolist", produces = "application/json")
    public ResponseEntity<?> updateTodo(@NonNull @RequestBody String jsonTodo) {
        Todo tempTodo;
        Type type = new TypeToken<Todo>() {
        }.getType();
        try {
            tempTodo = new Gson().fromJson(jsonTodo, type);
        } catch (Exception e) {
            log.error("error parsing post body", e);
            return getResponseJson(null, true, "error parsing request body", HttpStatus.OK);
        }

        todoList.replaceAll(todo -> todo.getId().equals(tempTodo.getId()) ? tempTodo : todo);
        async(() -> todoListService.updateTodoById(tempTodo));

        return getResponseJson(new Gson().toJson(todoList), null, "updated todo item in the list", HttpStatus.OK);
    }

    @DeleteMapping(path = "/api/todolist", produces = "application/json")
    public ResponseEntity<?> deleteCompletedTodo() {
        todoList.removeIf(Todo::getCompleted);
        todoListService.deleteCompletedTodo();

        return getResponseJson(new Gson().toJson(todoList), null, "deleted completed todo item from the list", HttpStatus.OK);

    }
}
