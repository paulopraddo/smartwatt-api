package com.fiap.SmartWatt.security.app.entrypoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.SmartWatt.security.app.exceptions.GenerationTokenException;
import com.fiap.SmartWatt.security.app.model.User;
import com.fiap.SmartWatt.security.app.repository.UserRepository;
import com.fiap.SmartWatt.security.app.restmodel.AuthenticationDTO;
import com.fiap.SmartWatt.security.app.restmodel.LoginResponseDTO;
import com.fiap.SmartWatt.security.app.restmodel.RegisterDTO;
import com.fiap.SmartWatt.security.infra.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authenticationDTO) {

        if (authenticationDTO.login() == null || authenticationDTO.login().isBlank() ||
                authenticationDTO.password() == null || authenticationDTO.password().isBlank()) {
                    return ResponseEntity.badRequest().body("Login e senha são obrigatórios.");
        }
        
        try {
            var usernamePassoword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
            var auth = this.authenticationManager.authenticate(usernamePassoword);
            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario ou senha invalidos");
        } catch (GenerationTokenException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao processar a solicitação");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        
        if(this.repository.findByLogin(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().body("Login cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User user = new User(registerDTO.login(), encryptedPassword, registerDTO.role());

        this.repository.save(user);

        return ResponseEntity.ok().body("Usuario criado com suecsso");
    }
}
