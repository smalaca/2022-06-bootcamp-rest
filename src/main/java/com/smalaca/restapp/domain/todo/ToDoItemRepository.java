package com.smalaca.restapp.domain.todo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {
    Iterable<ToDoItem> findAllByTitleOrAssignee(String title, String assignee);

    Iterable<ToDoItem> findAllByIdIn(List<Long> ids);
}
