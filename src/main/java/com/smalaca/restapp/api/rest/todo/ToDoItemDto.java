package com.smalaca.restapp.api.rest.todo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ToDoItemDto {
    private final Long id;
    private final String title;
    private final String description;
    private final String assignee;
}
