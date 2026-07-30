package com.rd.autopecas.erp_autopecas.domain.estoque_item.filter;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record EstoqueItemFilter(
        Long item,
        String nomeItem,
        String localizacao,
        @DecimalMin(value = "0.01")
        BigDecimal qtdMinima,
        @DecimalMin(value = "0.01")
        BigDecimal qtdMaxima
) {
}
