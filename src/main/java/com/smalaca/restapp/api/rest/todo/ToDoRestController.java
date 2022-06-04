package com.smalaca.restapp.api.rest.todo;

import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("todoitem")
public class ToDoRestController {
    @GetMapping
    public List<ToDoItemDto> findAll() {
        return ImmutableList.of(
                new ToDoItemDto(1L, "Let's do something", "fancy description", "Steve Rogers"),
                new ToDoItemDto(2L, "Lazy day", "nothing to do", "Tony Stark"),
                new ToDoItemDto(3L, "Anything", "description", "Odinson"),
                new ToDoItemDto(4L, "Nothing", "no description", "Black Pather")
        );
    }
}
