package com.yournal.yournal.Services;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String key;

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(),user);
    }

    private String getToken(Map<String,Object> extraClaims,UserDetails user){

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 *60*60*24))
                .signWith(getKey()).compact();
                
    }
    
    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
