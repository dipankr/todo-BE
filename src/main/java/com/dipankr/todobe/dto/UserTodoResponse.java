package com.dipankr.todobe.dto;

import com.dipankr.todobe.entity.UserTodo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTodoResponse {

    private List<UserTodo> data;
    @JsonInclude(Include.NON_EMPTY)
    private Boolean error;
    private String message;
}
