package com.yournal.yournal.DTO;

public class AuthDTO {

    public record Auth(
        String Token,
        String refreshToken
    ){}

    public record RefreshTokenRequest(String refreshToken){}
        

}
