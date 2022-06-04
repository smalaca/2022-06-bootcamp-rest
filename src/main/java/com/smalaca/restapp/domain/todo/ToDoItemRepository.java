package com.smalaca.restapp.domain.todo;

import org.springframework.data.repository.CrudRepository;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {
    Iterable<ToDoItem> findAllByTitleOrAssignee(String title, String assignee);
}
