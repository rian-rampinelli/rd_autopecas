package com.rd.autopecas.erp_autopecas.domain.auth.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;


public record RegisterRequest(
        @NotBlank(message = "Nome é obrigatório")
        String userName,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
        String cpf,

        @NotNull(message = "Salário é obrigatório")
        @Positive(message = "Salário deve ser maior que zero")
        BigDecimal salary,

        @NotEmpty(message = "Ao menos uma role deve ser informada")  // <-- aqui
        List<Long> roleIds
) {
}
