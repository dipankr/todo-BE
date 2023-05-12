package com.dipankr.todobe.service.impl;

import com.dipankr.todobe.dto.UserTodoRequest;
import com.dipankr.todobe.dto.UserTodoResponse;
import com.dipankr.todobe.entity.User;
import com.dipankr.todobe.entity.UserTodo;
import com.dipankr.todobe.repository.UserRepository;
import com.dipankr.todobe.service.UserTodoService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTodoServiceImpl implements UserTodoService {

    private final UserRepository userRepository;

    @Override
    public UserTodoResponse getAllUserTodos(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("No user with email " + userEmail));

        return UserTodoResponse.builder()
            .data(user.getUserTodos())
            .message("success")
            .build();
    }

    @Override
    public UserTodoResponse addUserTodo(String userEmail, UserTodoRequest userTodoRequest) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("No user with email " + userEmail));

        UserTodo newTodo = UserTodo.builder()
            .title(userTodoRequest.getTitle())
            .completed(userTodoRequest.isCompleted())
            .createdAt(LocalDateTime.now())
            .user(user)
            .build();
        user.getUserTodos().add(newTodo);
        userRepository.save(user);

        return UserTodoResponse.builder()
            .data(user.getUserTodos())
            .message("success")
            .build();
    }

    @Override
    public UserTodoResponse updateUserTodo(String userEmail, UserTodoRequest userTodoRequest) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("No user with email " + userEmail));

        user.getUserTodos().forEach(userTodo -> {
            if (userTodo.getId().equals(userTodoRequest.getId())) {
                if (null != userTodoRequest.getTitle()) {
                    userTodo.setTitle(userTodoRequest.getTitle());
                }
                userTodo.setCompleted(userTodoRequest.isCompleted());
            }
        });

        userRepository.save(user);

        return UserTodoResponse.builder()
            .data(user.getUserTodos())
            .message("success")
            .build();
    }

    @Override
    public UserTodoResponse deleteUserTodo(String userEmail, Long userTodoId) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("No user with email " + userEmail));

        user.getUserTodos().removeIf(todo -> todo.getId().equals(userTodoId));

        userRepository.save(user);

        return UserTodoResponse.builder()
            .data(user.getUserTodos())
            .message("success")
            .build();
    }

    @Override
    public UserTodoResponse deleteCompletedUserTodo(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("No user with email " + userEmail));

        user.getUserTodos().removeIf(UserTodo::isCompleted);

        userRepository.save(user);

        return UserTodoResponse.builder()
            .data(user.getUserTodos())
            .message("success")
            .build();
    }


}
