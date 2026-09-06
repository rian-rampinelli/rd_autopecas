package com.rd.autopecas.erp_autopecas.domain.auth.dto;

public record LoginResponse(
        String accessToken,
        Long expiresIn,
        Long id,
        String nome,
        String email,
        String cargo
) {
}
