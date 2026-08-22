package com.rd.autopecas.erp_autopecas.domain.funcionario.filter;

import java.math.BigDecimal;

public record FuncionarioFilter(
        String nome,
        String email,
        String cpf,
        String status,
        String cargo,
        BigDecimal minSalario,
        BigDecimal maxSalario
) {
}
