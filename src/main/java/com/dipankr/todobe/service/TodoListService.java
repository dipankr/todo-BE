package com.dipankr.todobe.service;

import com.dipankr.todobe.entity.Todo;
import java.util.ArrayList;
import java.util.List;

public class TodoListService {

    public static boolean deleteTodoById(List<Todo> todoList, String id) {
        if (todoList == null) {
            return false;
        }

        for (Todo todo : todoList) {
            if (todo.getId().equals(id)) {
                todoList.remove(todo);
                return true;
            }
        }

        return false;
    }

    public static boolean updateTodoById(ArrayList<Todo> todoList, String todoId, Todo tempTodo) {
        if (todoList == null) {
            return false;
        }
        if (tempTodo == null) {
            return false;
        }

        for (Todo todo : todoList) {
            if (todo.getId().equals(todoId)) {
                if (tempTodo.getTitle() != null && !tempTodo.getTitle().isEmpty()) {
                    todo.setTitle(tempTodo.getTitle());
                }
                if (tempTodo.getDescription() != null && !tempTodo.getDescription().isEmpty()) {
                    todo.setDescription(tempTodo.getDescription());
                }
                if (tempTodo.getCompleted() != null) {
                    todo.setCompleted(tempTodo.getCompleted());
                }
                return true;
            }
        }

        return false;
    }

    public static void deleteCompletedTodo(ArrayList<Todo> todoList) {
        todoList.removeIf(Todo::getCompleted);
    }
}
