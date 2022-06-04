package com.smalaca.restapp.api.rest.todo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

class ToDoRestControllerTest {
    private static final String URL = "http://localhost:8013/todoitem";
    private static final RestTemplate client = new RestTemplate();

    @BeforeAll
    static void beforeAll() {
        client.postForObject(URL, new ToDoItemTestDto("Let's do something", "fancy description", "Steve Rogers"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Lazy day", "nothing to do", "TonyStark"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Yet another lazy day", "nothing to do", "TonyStark"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Anything", "description", "Odinson"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Nothing", "no description", "Black Pather"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Nothing", "with description", "Black Widow"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Nothing", "some description", "Black Adam"), Long.class);
    }

    @Test
    void shouldReturnAllToDoItemsWithGivenIds() {
        ToDoItemTestDto[] result = client.getForObject(URL + "/by-ids?ids=1&ids=3&ids=6", ToDoItemTestDto[].class);

        Arrays.asList(result).forEach(System.out::println);
    }

    @Test
    void shouldReturnAllToDoItemsWithGivenTitle() {
        ToDoItemTestDto[] result = client.getForObject(URL + "?title=Nothing&assignee=TonyStark", ToDoItemTestDto[].class);

        Arrays.asList(result).forEach(System.out::println);
    }

    @Test
    void shouldReturnAllToDoItems() {
        ToDoItemTestDto[] result = client.getForObject(URL, ToDoItemTestDto[].class);

        Arrays.asList(result).forEach(System.out::println);
    }

    @Test
    void shouldUpdateSpecificItem() {
        Long id = client.postForObject(URL, new ToDoItemTestDto("Coding for fun", "Sages Bootcamp", "Charles Xavier"), Long.class);
        ToDoItemTestDto toDoItemTestDto = client.getForObject(URL + "/" + id, ToDoItemTestDto.class);
        Arrays.asList(toDoItemTestDto).forEach(System.out::println);

        client.put(URL + "/" + id, new ToDoItemTestDto("Sages Java Bootcamp", "Logan"));

        ToDoItemTestDto result = client.getForObject(URL + "/" + id, ToDoItemTestDto.class);
        Arrays.asList(result).forEach(System.out::println);
    }

    @Test
    void shouldReturnSpecificItem() {
        Long id = client.postForObject(URL, new ToDoItemTestDto("Get rest", "with something great to do", "Wanda Maximoff"), Long.class);

        ToDoItemTestDto result = client.getForObject(URL + "/" + id, ToDoItemTestDto.class);

        Arrays.asList(result).forEach(System.out::println);
    }

    @Test
    void shoulDeleteSpecificItem() {
        client.postForObject(URL, new ToDoItemTestDto("Anything", "description", "Natasha Romanoff"), Long.class);
        client.postForObject(URL, new ToDoItemTestDto("Nothing", "no description", "Riri Williams"), Long.class);
        Long id = client.postForObject(URL, new ToDoItemTestDto("Get rest", "with something great to do", "Carol Danver"), Long.class);
        ToDoItemTestDto[] all = client.getForObject(URL, ToDoItemTestDto[].class);
        Arrays.asList(all).forEach(System.out::println);

        client.delete(URL + "/" + id);

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

        public ToDoItemTestDto(String description, String assignee) {
            this.description = description;
            this.assignee = assignee;
        }
    }
}