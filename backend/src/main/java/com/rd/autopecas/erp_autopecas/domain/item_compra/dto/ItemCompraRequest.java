package com.rd.autopecas.erp_autopecas.domain.item_compra.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemCompraRequest(
        @NotNull
        Long idItem,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal quantidade,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal itemValue

) {

}
