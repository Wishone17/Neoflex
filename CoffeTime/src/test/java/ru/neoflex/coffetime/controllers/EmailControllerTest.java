package ru.neoflex.coffetime.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.neoflex.coffetime.DAO.PersonDAO;
import ru.neoflex.coffetime.service.EmailService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmailService emailService;

    @MockBean
    private PersonDAO personDAO;

    @Test
    void sendEmail() throws Exception {
        List<String> peopleEmail = Arrays.asList("alex@gmail.com","max@gmail.com");
        when(personDAO.getEmail()).thenReturn(peopleEmail);
        mvc.perform(MockMvcRequestBuilders.get("/sendEmail"))
                .andExpect(redirectedUrl("/people"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }
}
