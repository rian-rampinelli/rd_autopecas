package com.rd.autopecas.erp_autopecas.domain.funcionario.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record FuncionarioUpdateRequest(
        String nome,
        String email,
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
        String cpf,
        @Min(value = 0, message = "Salário deve ser maior que zero")
        BigDecimal salary,
        String cargo
) {
}
