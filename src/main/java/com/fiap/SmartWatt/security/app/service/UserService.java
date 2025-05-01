package com.fiap.SmartWatt.security.app.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fiap.SmartWatt.security.app.exceptions.InvalidCredentialsException;
import com.fiap.SmartWatt.security.app.exceptions.InvalidUserDataException;
import com.fiap.SmartWatt.security.app.exceptions.UserAlreadyExistsException;
import com.fiap.SmartWatt.security.app.model.User;
import com.fiap.SmartWatt.security.app.model.UserRole;
import com.fiap.SmartWatt.security.app.repository.UserRepository;
import com.fiap.SmartWatt.security.app.restmodel.LoginResponseDTO;
import com.fiap.SmartWatt.security.infra.security.TokenService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginResponseDTO login(String login, String password) {
        if (login == null || login.isBlank()) throw new InvalidUserDataException("Login obrigatório");
        if (password == null || password.isBlank()) throw new InvalidUserDataException("Senha obrigatória");
        
        
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(login, password);
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());

            return new LoginResponseDTO(token);
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Usuario ou senha incorretos");
        }
    }
    
    public void registerUser(String login, String password, String userRoleStr) {

        if (login == null || login.isBlank()) throw new InvalidUserDataException("Login obrigatório");
        if (password == null || password.isBlank()) throw new InvalidUserDataException("Senha obrigatória");
        if (userRoleStr == null || userRoleStr.isBlank()) throw new InvalidUserDataException("Perfil de usuário obrigatório");

        if (userRepository.findByLogin(login) != null) {
            throw new UserAlreadyExistsException("Login já está sendo utilizado");
        }

        UserRole role = UserRole.fromString(userRoleStr);
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        User user = new User(login, encryptedPassword, role);

        this.userRepository.save(user);
    }
}
