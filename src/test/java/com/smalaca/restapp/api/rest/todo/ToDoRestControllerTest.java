package com.smalaca.restapp.api.rest.todo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

class ToDoRestControllerTest {
    private static final String URL = "http://localhost:8013/todoitem";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnAllToDoItems() {
        client.postForObject(URL, new ToDoItemTestDto("Let's do something", "fancy description", "Steve Rogers"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Lazy day", "nothing to do", "Tony Stark"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Anything", "description", "Odinson"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Nothing", "no description", "Black Pather"), Long.class);

        ToDoItemTestDto[] result = client.getForObject(URL, ToDoItemTestDto[].class);

        Arrays.asList(result).forEach(System.out::println);
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class ToDoItemTestDto {
        private Long id;
        private String title;
        private String description;
        private String assignee;

        public ToDoItemTestDto(String title, String description, String assignee) {
            this.title = title;
            this.description = description;
            this.assignee = assignee;
        }
    }
}