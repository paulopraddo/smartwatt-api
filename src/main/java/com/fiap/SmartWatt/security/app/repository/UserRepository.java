package com.fiap.SmartWatt.security.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.fiap.SmartWatt.security.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
