package com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.filter;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record MovimentacaoEstoqueFilter(
        Long item,
        String tipo,
        @DecimalMin(value = "0.01")
        BigDecimal qtdMinima
) {
}
