package com.dipankr.todobe.controller;

import static com.dipankr.todobe.wrapper.ResponseWrapper.getResponseJson;

import com.dipankr.todobe.entity.Todo;
import com.dipankr.todobe.service.AsyncThreadService;
import com.dipankr.todobe.service.TodoListService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/todolist")
public class TodoListController {

    private final TodoListService todoListService;
    private final AsyncThreadService asyncThreadService;
    private final List<Todo> todoList;

    public TodoListController(TodoListService todoListService, AsyncThreadService asyncThreadService) {
        this.todoListService = todoListService;
        this.asyncThreadService = asyncThreadService;
        this.todoList = todoListService.getAllTodo();
    }

    /**
     * Fetches all the todoList items
     *
     * @return the complete todoList in JSON format
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getTodoList() {
        return getResponseJson(todoList, null, "success", HttpStatus.OK);
    }

    /**
     * Add the given todoItem to the list
     *
     * @param jsonTodo new todoItem in JSON format
     * @return todolist with the newly added item
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addTodo(@NonNull @RequestBody String jsonTodo) {
        Todo tempTodo;
        try {
            Type type = new TypeToken<Todo>() {
            }.getType();
            tempTodo = new Gson().fromJson(jsonTodo, type);

        } catch (Exception e) {
            log.error("error parsing post body", e);
            return getResponseJson(todoList, true, "error parsing request body", HttpStatus.BAD_REQUEST);
        }

        if (null == tempTodo) {
            return getResponseJson(todoList, true, "invalid data", HttpStatus.NOT_ACCEPTABLE);
        }
        if (null == tempTodo.getId()) {
            return getResponseJson(todoList, true, "ID not valid", HttpStatus.NOT_ACCEPTABLE);
        }
        if (null == tempTodo.getTitle() || 0 == tempTodo.getTitle().length()) {
            return getResponseJson(todoList, true, "title can not be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        todoList.add(tempTodo);
        asyncThreadService.async(() -> todoListService.addTodo(tempTodo));

        return getResponseJson(todoList, null, "added todo item to the list", HttpStatus.OK);
    }

    /**
     * Updates the given todoItem in the list
     *
     * @param jsonTodo the todoItem to update
     * @return todolist with the newly updated item
     */
    @PutMapping(produces = "application/json")
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
        asyncThreadService.async(() -> todoListService.updateTodoById(tempTodo));

        return getResponseJson(todoList, null, "updated todo item in the list", HttpStatus.OK);
    }

    /**
     * Deletes a todoItem in the list with matching id
     *
     * @param todoId id of the todoItem to be deleted
     * @return todolist with a todoItem deleted with matching todoId
     */
    @DeleteMapping(path = "/{todoId}", produces = "application/json")
    public ResponseEntity<?> deleteTodo(@PathVariable @NonNull String todoId) {
        todoList.removeIf(todo -> todo.getId().equals(todoId));
        asyncThreadService.async(() -> todoListService.deleteTodoById(todoId));

        return getResponseJson(todoList, null, "deleted todo item from the list", HttpStatus.OK);
    }

    /**
     * Deletes all the todoItems in the list which are completed
     *
     * @return todolist with the only non-completed todoItems
     */
    @DeleteMapping(produces = "application/json")
    public ResponseEntity<?> deleteCompletedTodo() {
        todoList.removeIf(Todo::getCompleted);
        todoListService.deleteCompletedTodo();

        return getResponseJson(todoList, null, "deleted completed todo item from the list", HttpStatus.OK);

    }
}
