package com.yournal.yournal.Services;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService 
{

    @Value("${jwt.secret}")
    private String key;

    private static final long REFRESH_TOKEN_EXPIRATION= 1000*60*60*24*60;
    private static final long ACCESS_TOKEN_EXPIRATION= 1000*60*60*24*7;

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(),user,ACCESS_TOKEN_EXPIRATION);
    }
      public String refreshToken(UserDetails user){
        return getToken(new HashMap<>(),user,REFRESH_TOKEN_EXPIRATION);
    }

    private String getToken(Map<String,Object> extraClaims,UserDetails user, long expiration){

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey()).compact();
                
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims getAllClaims(String token){
        return Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token).getPayload();
    }
    public <T> T getClaim(String token, Function<Claims,T>claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String getUsernameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

}





