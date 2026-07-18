package com.rd.autopecas.erp_autopecas.domain.auth.dto;

import com.rd.autopecas.erp_autopecas.domain.role.Role;

import java.math.BigDecimal;


public record RegisterRequest(
        String userName,
        String email,
        String passWord,
        String cpf,
        BigDecimal salary,
        String role
) {
}
