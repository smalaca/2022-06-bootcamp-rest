package com.smalaca.restapp.api.rest.todo;

import com.smalaca.restapp.api.rest.NotFoundEntityException;
import com.smalaca.restapp.domain.todo.ToDoItem;
import com.smalaca.restapp.domain.todo.ToDoItemDto;
import com.smalaca.restapp.domain.todo.ToDoItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("todoitem")
public class ToDoRestController {
    private final ToDoItemRepository repository;

    public ToDoRestController(ToDoItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("by-ids")
    public List<ToDoItemDto> findAllByIds(@RequestParam MultiValueMap<String, String> params) {
        List<Long> ids = params.get("ids").stream().map(Long::parseLong).collect(Collectors.toList());

        Iterable<ToDoItem> iterable= repository.findAllByIdIn(ids);

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(ToDoItem::asDto)
                .collect(Collectors.toList());
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
        Optional<ToDoItem> found = repository.findById(id);

        if (found.isPresent()) {
            return found.get().asDto();
        } else {
            throw new NotFoundEntityException(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("ToDoItem removed");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("ToDoItem does not exist");
        }
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody ToDoItemDto dto) {
        ToDoItem toDoItem = repository.findById(id).get();
        toDoItem.update(dto);

        repository.save(toDoItem);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(
            @RequestBody ToDoItemDto dto,
            @RequestHeader Map<String, String> headers,
            HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("from-server", "cookie-monster");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(100);
        response.addCookie(cookie);

        ToDoItem toDoItem = new ToDoItem(dto.getTitle(), dto.getDescription(), dto.getAssignee());
        return repository.save(toDoItem).getId();
    }
}
