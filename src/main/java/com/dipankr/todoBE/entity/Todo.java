package com.dipankr.todoBE.entity;

import com.dipankr.todoBE.service.IDService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Todo {
    private final String id;
    private String title;
    private String description;
    private Boolean completed;

    public Todo(String title, String description) {
        this.id = String.valueOf(IDService.getInstance().getID());
        this.title = title;
        this.description = description;
        this.completed = Boolean.FALSE;
    }

    public boolean equalsValue(Todo todo){
        return this.title.equals(todo.getTitle()) &&
                this.description.equals(todo.getDescription()) &&
                this.completed.equals(todo.getCompleted());
    }
}
