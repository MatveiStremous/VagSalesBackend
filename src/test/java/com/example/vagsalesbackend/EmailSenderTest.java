package com.example.vagsalesbackend;

import com.example.vagsalesbackend.util.mail.MailSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootTest
public class EmailSenderTest {
    @Autowired
    MailSender mailSender;

    @Test
    @Timeout(1000)
    public void getConnectionShouldBeFasterThenOneSecond() {
        try {
            mailSender.send("test@gmail.com", "Test", "Test");
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
