package com.rd.autopecas.erp_autopecas.security;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;


@AllArgsConstructor
@Component
public class TokenProvider {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;


    //gerar um token
    public String gerarToken(Authentication authentication){
        Funcionario funcionario =
                (Funcionario) authentication.getPrincipal();

        Instant now = Instant.now();
        var expiresIn = 1200L;

        var claims = JwtClaimsSet.builder()
                .subject(funcionario.getUsername())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }

    public String getUsername(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    public Long expiresIn(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        Instant expiration = jwt.getExpiresAt();
        return expiration.toEpochMilli();
    }

    public boolean isTokenValid(String token) {
        try {
            jwtDecoder.decode(token);
            return true;

        } catch (JwtException e) {
            return false;
        }
    }

}
