package com.yournal.yournal.Models;



import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 
@Table(name="t_user")
public class User implements UserDetails{

@Id 
@GeneratedValue 
Integer id;
@Column(nullable=false, unique=true)
String username;
String email;
String password;
@Enumerated(EnumType.STRING)
    private Role role; // Añade este campo

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security leerá el rol desde aquí
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // ¡IMPORTANTE! Estos 4 métodos deben devolver 'true'
    @Override
    public boolean isAccountNonExpired() {
        return true; 
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
