package com.smalaca.restapp.domain.todo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ToDoItem {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String assignee;

    public ToDoItem(String title, String description, String assignee) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
    }

    public ToDoItemDto asDto() {
        return new ToDoItemDto(id, title, description, assignee);
    }
}
