package com.fiap.SmartWatt.security.app.entrypoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.SmartWatt.security.app.restmodel.AuthenticationDTO;
import com.fiap.SmartWatt.security.app.restmodel.LoginResponseDTO;
import com.fiap.SmartWatt.security.app.restmodel.RegisterDTO;
import com.fiap.SmartWatt.security.app.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO authenticationDTO) {
        LoginResponseDTO loginResponseDTO = this.userService.login(authenticationDTO.login(), authenticationDTO.password());
        return ResponseEntity.ok().body(loginResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        this.userService.registerUser(registerDTO.login(), registerDTO.password(), registerDTO.userRole());
        return ResponseEntity.ok().body("Usuario criado com suecsso");
    }
}
