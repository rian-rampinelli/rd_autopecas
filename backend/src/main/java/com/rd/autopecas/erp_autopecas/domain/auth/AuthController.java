package com.rd.autopecas.erp_autopecas.domain.auth;

import com.rd.autopecas.erp_autopecas.domain.auth.dto.LoginRequest;
import com.rd.autopecas.erp_autopecas.domain.auth.dto.LoginResponse;
import com.rd.autopecas.erp_autopecas.domain.auth.dto.RegisterRequest;
import com.rd.autopecas.erp_autopecas.domain.auth.dto.RegisterResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController  {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
    }
}
