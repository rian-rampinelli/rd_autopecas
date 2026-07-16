package com.rd.autopecas.erp_autopecas.domain.auth.dto;

public record LoginResponse(
        String accessToken,
        Long expiresIn
) {
}
