package com.example.springsecurityjwtauthentication.payload;

import lombok.Data;
import org.hibernate.annotations.NotFound;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
   @NotBlank
   private String username;

   @NotBlank
    private String password;

}
