package com.smalaca.restapp.domain.todo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // serialization & deserialization
@NoArgsConstructor // deserialization
@AllArgsConstructor(access = AccessLevel.PACKAGE) // using in the code
public class ToDoItemDto {
    private Long id;
    private String title;
    private String description;
    private String assignee;
}
