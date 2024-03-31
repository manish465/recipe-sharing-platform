package com.manish.auth.service;

import com.manish.auth.exception.ApplicationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AuthService {

    private static final long EXPIRATION_TIME = 864_000_000;
    private static final String SECRET = "6823474933462348372493896234378472934";


    public String generateToken(String userId){
        log.info("|| generateToken is called from AuthService class ||");

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String extractUser(String token) {
        log.info("|| extractUser is called from AuthService class ||");

        String userId;
        
        try {
            userId = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (SignatureException e){
            throw  new ApplicationException("Invalid Token");
        }

        return userId;
    }
}
