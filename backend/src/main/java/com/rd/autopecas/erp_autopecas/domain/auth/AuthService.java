package com.rd.autopecas.erp_autopecas.domain.auth;

import com.rd.autopecas.erp_autopecas.domain.auth.dto.LoginRequest;
import com.rd.autopecas.erp_autopecas.domain.auth.dto.LoginResponse;
import com.rd.autopecas.erp_autopecas.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) throws Exception{
        try {
            //authentication provider -> userDetailsService -> PassWordEncoder.matches -> autenticado!
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.userName(),loginRequest.passWord()));
            String token = tokenProvider.gerarToken(authentication);

            return new LoginResponse(token, tokenProvider.expiresIn(token));
        }catch (BadCredentialsException e){
            throw new BadRequestException("credenciasl invalidas");
        }catch (Exception e){
            throw e;
        }
    }
}
