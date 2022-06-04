package com.smalaca.restapp.api.rest.todo;

import com.smalaca.restapp.domain.todo.ToDoItem;
import com.smalaca.restapp.domain.todo.ToDoItemDto;
import com.smalaca.restapp.domain.todo.ToDoItemRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
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
    public List<ToDoItemDto> findAll(@RequestParam Map<String, String> params) {
        Iterable<ToDoItem> iterable;

        if (!params.containsKey("title") && !params.containsKey("assignee")) {
            iterable = repository.findAll();
        } else {
            iterable = repository.findAllByTitleOrAssignee(params.get("title"), params.get("assignee"));
        }

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(ToDoItem::asDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ToDoItemDto findById(@PathVariable Long id) {
        return repository.findById(id).get().asDto();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody ToDoItemDto dto) {
        ToDoItem toDoItem = repository.findById(id).get();
        toDoItem.update(dto);

        repository.save(toDoItem);
    }

    @PostMapping
    public Long create(@RequestBody ToDoItemDto dto) {
        ToDoItem toDoItem = new ToDoItem(dto.getTitle(), dto.getDescription(), dto.getAssignee());

        return repository.save(toDoItem).getId();
    }
}
