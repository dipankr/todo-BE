package com.dipankr.todobe.service;

import com.dipankr.todobe.entity.Todo;
import java.util.List;

public interface TodoListService {

    List<Todo> getAllTodo();

    void addTodo(Todo newTodo);

    void deleteTodoById(String id);

    void updateTodoById(Todo tempTodo);

    void deleteCompletedTodo();
}
