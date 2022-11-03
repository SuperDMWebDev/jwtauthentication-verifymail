package com.example.springsecurityjwtauthentication;

import com.example.springsecurityjwtauthentication.jwt.JwtTokenProvider;
import com.example.springsecurityjwtauthentication.payload.LoginRequest;
import com.example.springsecurityjwtauthentication.payload.LoginResponse;
import com.example.springsecurityjwtauthentication.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class WebRestController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("login api");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()
        ));
        // if nothing happen -> authentication is successful
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //send jwt for user
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @GetMapping("/random")
    public String random()
    {
        System.out.println("random api");
        return "Access random";
    }
    @GetMapping("/send-mail")
    public void sendPlainTextMail(Model model) {
        String from = "hadtnt71@gmail.com";
        String to = "hadtnt73@gmail.com";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("This is subject");
        message.setText("This is body");

        mailSender.send(message);
    }

}

