package ru.neoflex.coffetime.service;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(List<String> emailList) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        for(String email : emailList) {
            helper.setTo(email);
            helper.setText("Доброе утро! Пошли пить кофе!");
            helper.setSubject("Приглашение");
            mailSender.send(message);
        }
    }
}
