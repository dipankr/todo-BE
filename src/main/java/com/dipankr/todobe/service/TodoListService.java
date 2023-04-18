package com.dipankr.todobe.service;

import com.dipankr.todobe.entity.Todo;
import com.dipankr.todobe.repository.ITodoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TodoListService {

    private final ITodoRepository todoRepository;

    @Autowired
    public TodoListService(ITodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public void addTodo(Todo newTodo) {
        todoRepository.save(newTodo);
    }

    public void deleteTodoById(String id) {
        todoRepository.deleteById(id);
    }

    public void updateTodoById(Todo tempTodo) {
        todoRepository.save(tempTodo);
    }

    public void deleteCompletedTodo() {
        todoRepository.deleteCompleted();
    }
}
