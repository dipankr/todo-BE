package com.dipankr.todobe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todolist")
@Deprecated
public class Todo {

    @Id
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "completed")
    private Boolean completed = false;

}
