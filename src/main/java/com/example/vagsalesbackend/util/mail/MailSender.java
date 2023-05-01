package com.example.vagsalesbackend.util.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class MailSender {
    private final JavaMailSender mailSender;
    private final String username = "VagSalesManagers@gmail.com";

    @Autowired
    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String emailTo, String subject, String message) throws MessagingException, IOException {
        MimeMessage messageTo = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messageTo, true);
        helper.setFrom(username);
        helper.setTo(emailTo);
        helper.setSubject(subject);
        helper.setText(message);
        mailSender.send(messageTo);
    }
}
