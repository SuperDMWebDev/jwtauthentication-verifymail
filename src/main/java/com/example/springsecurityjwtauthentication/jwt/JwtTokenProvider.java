package com.example.springsecurityjwtauthentication.jwt;

import com.example.springsecurityjwtauthentication.user.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    private final String JWT_SECRET = "jwt-secret";

    private final long JWT_EXPIRATION = 60480000L; // milliseconds

    //create json web token from userId
    public String generateToken(CustomUserDetails userDetails){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() +JWT_EXPIRATION);
        //actually build the jwt and serialize it into compact, URL-safe string according to jwt compact serialization
        return Jwts.builder().setSubject(Long.toString(userDetails.getUser().getId())).setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512,JWT_SECRET).compact();
    }
    //get user info from jwt
    public Long getUserIdFromJwt(String token)
    {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public Boolean validateToken(String authToken)
    {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }catch(Exception e)
        {
            log.error("JWT validate error");
        }
        return false;
    }

}
