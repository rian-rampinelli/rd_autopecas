package com.rd.autopecas.erp_autopecas.domain.funcionario.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public record FuncionarioRequest(

        @NotNull(message = "Salário não pode ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "Salário deve ser maior que zero")
        BigDecimal salario,
        
        @NotNull(message = "IDs das roles não podem ser nulos")
        Set<Long> roles
) {
}
