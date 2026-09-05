package com.rd.autopecas.erp_autopecas.domain.item_venda.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemVendaRequest(
        @NotNull
        Long idItem,
        @NotNull
        Long idEstoque,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal quantidade,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal itemValue

) {

}
