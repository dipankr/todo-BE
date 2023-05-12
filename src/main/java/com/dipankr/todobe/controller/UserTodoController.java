package com.dipankr.todobe.controller;

import com.dipankr.todobe.dto.UserTodoRequest;
import com.dipankr.todobe.service.UserTodoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userTodo")
@RequiredArgsConstructor
public class UserTodoController {

    private final UserTodoService userTodoService;

    /**
     * Fetches all the todoList items
     *
     * @return UserTodoResponse in ResponseEntity with complete UserTodoList
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getTodoList(@Context HttpServletRequest request) {
        String username = request.getUserPrincipal().getName(); // username is email
        return ResponseEntity.ok(userTodoService.getAllUserTodos(username));
    }

    /**
     * Add the given userTodo to the list
     *
     * @param userTodoRequest new userTodo
     * @return UserTodoResponse in ResponseEntity with the newly added item
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addTodo(@NonNull @RequestBody UserTodoRequest userTodoRequest, @Context HttpServletRequest request) {
        String username = request.getUserPrincipal().getName(); // username is email
        return ResponseEntity.ok(userTodoService.addUserTodo(username, userTodoRequest));
    }

    /**
     * Updates the given userTodo in the list
     *
     * @param userTodoRequest the userTodo to update
     * @return UserTodoResponse in ResponseEntity with the newly updated item
     */
    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateTodo(@NonNull @RequestBody UserTodoRequest userTodoRequest, @Context HttpServletRequest request) {
        String username = request.getUserPrincipal().getName(); // username is email
        return ResponseEntity.ok(userTodoService.updateUserTodo(username, userTodoRequest));
    }

    /**
     * Deletes a userTodo in the list with matching id
     *
     * @param userTodoId id of the userTodo to be deleted
     * @return UserTodoResponse in ResponseEntity with a userTodo deleted with matching todoId
     */
    @DeleteMapping(path = "/{userTodoId}", produces = "application/json")
    public ResponseEntity<?> deleteTodo(@PathVariable @NonNull Long userTodoId, @Context HttpServletRequest request) {
        String username = request.getUserPrincipal().getName(); // username is email
        return ResponseEntity.ok(userTodoService.deleteUserTodo(username, userTodoId));
    }

    /**
     * Deletes all the userTodos in the list which are completed
     *
     * @return UserTodoResponse in ResponseEntity with the only non-completed userTodos
     */
    @DeleteMapping(produces = "application/json")
    public ResponseEntity<?> deleteCompletedTodo(@Context HttpServletRequest request) {
        String username = request.getUserPrincipal().getName(); // username is email
        return ResponseEntity.ok(userTodoService.deleteCompletedUserTodo(username));
    }
}
