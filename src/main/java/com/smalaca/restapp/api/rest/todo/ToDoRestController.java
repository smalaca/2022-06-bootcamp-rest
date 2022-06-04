package com.smalaca.restapp.api.rest.todo;

import com.smalaca.restapp.domain.todo.ToDoItem;
import com.smalaca.restapp.domain.todo.ToDoItemDto;
import com.smalaca.restapp.domain.todo.ToDoItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("todoitem")
public class ToDoRestController {
    private final ToDoItemRepository repository;

    public ToDoRestController(ToDoItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ToDoItemDto> findAll() {
        Iterable<ToDoItem> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(ToDoItem::asDto)
                .collect(Collectors.toList());
//        return ImmutableList.of(
//                new ToDoItemDto(1L, "Let's do something", "fancy description", "Steve Rogers"),
//                new ToDoItemDto(2L, "Lazy day", "nothing to do", "Tony Stark"),
//                new ToDoItemDto(3L, "Anything", "description", "Odinson"),
//                new ToDoItemDto(4L, "Nothing", "no description", "Black Pather")
//        );
    }
}
