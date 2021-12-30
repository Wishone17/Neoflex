package ru.neoflex.coffetime.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.neoflex.coffetime.DAO.PersonDAO;
import ru.neoflex.coffetime.service.EmailService;

import javax.mail.MessagingException;
import java.util.List;

@Controller
public class EmailController {

    final EmailService emailService;
    final PersonDAO personDAO;

    public EmailController(EmailService emailService, PersonDAO personDAO) {
        this.emailService = emailService;
        this.personDAO = personDAO;
    }

    @GetMapping("/sendEmail")
    public String sendEmail(){
        List<String> emailList = personDAO.getEmail();
        try {
            emailService.sendEmail(emailList);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/people";
    }
}
