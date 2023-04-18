package com.dipankr.todobe.repository;

import com.dipankr.todobe.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITodoRepository extends JpaRepository<Todo, String> {

    @Modifying
    @Query("delete from Todo td where td.completed = true")
    void deleteCompleted();
}
