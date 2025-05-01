package com.fiap.SmartWatt.security.app.entrypoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fiap.SmartWatt.security.app.restmodel.AuthenticationDTO;
import com.fiap.SmartWatt.security.app.restmodel.LoginResponseDTO;
import com.fiap.SmartWatt.security.app.restmodel.RegisterDTO;
import com.fiap.SmartWatt.security.app.service.UserService;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    String validLogin = "validLogin";
    String validPassword = "validPassword";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void shouldReturnLoginResponseDTOWhenCredentialsAreValid() {
        AuthenticationDTO dto = new AuthenticationDTO(validLogin, validPassword);
        LoginResponseDTO expectedResponseDTO = new LoginResponseDTO("tokenResponse");

        when(this.userService.login(dto.login(), dto.password())).thenReturn(expectedResponseDTO);

        ResponseEntity<LoginResponseDTO> response = this.authController.login(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponseDTO, response.getBody());
        verify(this.userService, times(1)).login(anyString(), anyString());
    }

    @Test
    void shouldReturnOKWhenUserIsCreated() {
        RegisterDTO dto = new RegisterDTO(validLogin, validPassword, "ADMIN");

        ResponseEntity<String> response = this.authController.register(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(this.userService, times(1)).registerUser(anyString(), anyString(), anyString());
    }
}
