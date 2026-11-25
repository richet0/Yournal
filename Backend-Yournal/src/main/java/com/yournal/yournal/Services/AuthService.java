package com.yournal.yournal.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yournal.yournal.Auth.AuthResponse;
import com.yournal.yournal.Auth.LoginRequest;
import com.yournal.yournal.Auth.RegisterRequest;
import com.yournal.yournal.DTO.AuthDTO.RefreshTokenRequest;
import com.yournal.yournal.DTO.AuthDTO.Auth;
import com.yournal.yournal.Models.Role;
import com.yournal.yournal.Models.User;
import com.yournal.yournal.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;


    public AuthResponse login(LoginRequest request) {
       authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
       UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
       String token = jwtService.getToken(user);
       return AuthResponse.builder()
                        .token(token)
                        .build();
    }

    public Auth refreshToken(RefreshTokenRequest request){

        final String refreshToken = request.refreshToken();
        final String username = jwtService.getUsernameFromToken(refreshToken);
        if(username!=null){
           UserDetails user = userRepository.findByUsername(username)
                                            .orElseThrow(()-> new UsernameNotFoundException("User not found"));
       
       
       if(jwtService.isTokenValid(refreshToken, user)){
            String newToken = jwtService.refreshToken(user);
            return new Auth(newToken,refreshToken);
       }
            
    }
        throw new RuntimeException("Invalid or expired refresh");

    }

   public AuthResponse register(RegisterRequest request) {
    try {
       
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    } catch (Exception e) {
       
        e.printStackTrace(); 
        throw e;
    }
}

}