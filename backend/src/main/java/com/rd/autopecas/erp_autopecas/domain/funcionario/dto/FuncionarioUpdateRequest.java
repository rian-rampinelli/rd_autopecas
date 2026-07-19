package com.rd.autopecas.erp_autopecas.domain.funcionario.dto;

import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public record FuncionarioUpdateRequest(
        @NotNull(message = "Status não pode ser nulo")
        StatusFuncionario status,
        
        @NotNull(message = "Salário não pode ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "Salário deve ser maior que zero")
        BigDecimal salario
) {
}
