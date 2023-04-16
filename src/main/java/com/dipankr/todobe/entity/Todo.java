package com.dipankr.todobe.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Todo {
    private String id;
    private String title;
    private Boolean completed;

    public boolean equalsValue(Todo todo){
        return this.title.equals(todo.getTitle()) &&
                this.completed.equals(todo.getCompleted());
    }
}
