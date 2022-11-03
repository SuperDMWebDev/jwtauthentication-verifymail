package com.example.springsecurityjwtauthentication;

import com.example.springsecurityjwtauthentication.user.User;
import com.example.springsecurityjwtauthentication.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootApplication
public class SpringSecurityJwtAuthenticationApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtAuthenticationApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// Khi chương trình chạy
		// Insert vào csdl một user.
		try{
			User user = new User();
			user.setUsername("loda");
			user.setPassword(passwordEncoder.encode("loda"));
			userRepository.save(new User(0L,"loda",passwordEncoder.encode("loda")));
//			System.out.println(user);
			String from = "sender@gmail.com";
			String to = "recipient@gmail.com";

//			SimpleMailMessage message = new SimpleMailMessage();
//
//			message.setFrom(from);
//			message.setTo(to);
//			message.setSubject("This is a plain text email");
//			message.setText("Hello guys! This is a plain text email.");
//
//			mailSender.send(message);
		}catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}
	}
}
