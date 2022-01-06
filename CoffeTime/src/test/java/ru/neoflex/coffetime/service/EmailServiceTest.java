package ru.neoflex.coffetime.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import ru.neoflex.coffetime.DAO.PersonDAO;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    private MimeMessage mimeMessage;

    private List<String> emailList;

    @Test
    void sendEmailTest() throws Exception {
        emailList = new ArrayList<>();
        emailList.add("makar@gmail.com");
        emailList.add("ulig98@gmail.com");
        emailList.add("vasiliev@gmail.com");

        mimeMessage = new MimeMessage((Session) null);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        emailService.sendEmail(emailList);
        assertEquals("vasiliev@gmail.com", mimeMessage.getRecipients(MimeMessage.RecipientType.TO)[0].toString());
    }
}
