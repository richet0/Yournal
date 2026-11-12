package com.yournal.yournal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yournal.yournal.Models.User;

import java.util.Optional;




public interface UserRepository extends JpaRepository<User,Integer>{

Optional<User>findByUsername(String username);
}