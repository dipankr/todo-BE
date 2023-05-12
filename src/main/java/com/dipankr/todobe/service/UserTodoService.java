package com.dipankr.todobe.service;

import com.dipankr.todobe.dto.UserTodoRequest;
import com.dipankr.todobe.dto.UserTodoResponse;

public interface UserTodoService {

    UserTodoResponse getAllUserTodos(String userEmail);

    UserTodoResponse addUserTodo(String userEmail, UserTodoRequest userTodoRequest);

    UserTodoResponse updateUserTodo(String userEmail, UserTodoRequest userTodoRequest);

    UserTodoResponse deleteUserTodo(String userEmail, Long userTodoId);

    UserTodoResponse deleteCompletedUserTodo(String userEmail);
}
