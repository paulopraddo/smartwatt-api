package com.fiap.SmartWatt.security.app.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.fiap.SmartWatt.security.app.exceptions.InvalidCredentialsException;
import com.fiap.SmartWatt.security.app.exceptions.InvalidUserDataException;
import com.fiap.SmartWatt.security.app.repository.UserRepository;
import com.fiap.SmartWatt.security.infra.security.TokenService;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenService tokenService;

    private String login = "validLogin";
    private String validPassword = "validPassword";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowInvalidUserDataExceptionWhenLoginIsBlank() {
        assertThrows(InvalidUserDataException.class, () -> {
            userService.login(" ", validPassword);
        });
    }

    @Test
    void shouldThrowInvalidUserDataExceptionWhenPasswordIsBlank() {
        assertThrows(InvalidUserDataException.class, () -> {
            userService.login(login, " ");
        });
    }

    @Test
    void shouldThrowInvalidCredentialsExceptionWhenAuthenticationFails() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenThrow(new BadCredentialsException("Bad credentials"));

        assertThrows(InvalidCredentialsException.class, () -> {
            userService.login(login, "wrongPassword");
        });
    }

}
