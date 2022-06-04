package com.smalaca.restapp.api.rest.todo;

import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

class ToDoRestControllerTest {
    private static final String URL = "http://localhost:8013/todoitem";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnAllToDoItems() {
        ToDoItemTestDto[] result = client.getForObject(URL, ToDoItemTestDto[].class);

        Arrays.asList(result).forEach(System.out::println);
    }

    @Getter
    @ToString
    public static class ToDoItemTestDto {
        private Long id;
        private String title;
        private String description;
        private String assignee;
    }
}