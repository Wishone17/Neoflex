package ru.neoflex.coffetime.controllers;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.neoflex.coffetime.DAO.PersonDAO;
import ru.neoflex.coffetime.models.Person;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeopleController.class)
class PeopleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonDAO personDAO;

    @Test
    void getPersonList() throws Exception {
        List<Person> people = Arrays.asList(new Person(1, "Илья", "Александров", "alex@gmail.com"),
                new Person(2, "Владимир", "Максимов", "max@gmail.com"));
        when(personDAO.getPeople()).thenReturn(people);
        mvc.perform(MockMvcRequestBuilders.get("/people"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("persons-list"))
                .andExpect(MockMvcResultMatchers.model().attribute("people", Matchers.hasSize(2)));
    }

    @Test
    void addPerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/people/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void createPerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/people")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void edit() throws Exception{
        List<Person> people = Arrays.asList(new Person(1, "Илья", "Александров", "alex@gmail.com"),
                new Person(2, "Владимир", "Максимов", "max@gmail.com"));
        when(personDAO.showPerson(0)).thenReturn(people.get(0));
        mvc.perform(MockMvcRequestBuilders.get("/people/edit/{id}", 0))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("edit"));
    }



    @Test
    void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/people/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deletePerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/people/delete/{id}", 1))
                .andExpect(redirectedUrl("/people"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }
}

