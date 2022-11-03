package com.example.springsecurityjwtauthentication.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class MailComponent {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail()
    {

    }
}
