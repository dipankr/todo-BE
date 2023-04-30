package com.dipankr.todobe.service;

import com.dipankr.todobe.entity.Todo;
import java.util.List;

public interface TodoListService {

    /**
     * Fetches a list of all the todoItems
     *
     * @return list of all todoItems
     */
    List<Todo> getAllTodo();

    /**
     * Adds a todoItem to the list
     *
     * @param newTodo a new todoItem
     */
    void addTodo(Todo newTodo);

    /**
     * Removes a todoItem from the list
     *
     * @param id of the todoItem to remove
     */
    void deleteTodoById(String id);

    /**
     * Updates a todoItem
     *
     * @param tempTodo todoItem to with updated values
     */
    void updateTodoById(Todo tempTodo);

    /**
     * Removes all the completed todoItems
     */
    void deleteCompletedTodo();
}
