package com.rd.autopecas.erp_autopecas.domain.item_compra.dto;

import com.rd.autopecas.erp_autopecas.domain.compra.Compra;
import com.rd.autopecas.erp_autopecas.domain.compra.dto.CompraResponse;
import com.rd.autopecas.erp_autopecas.domain.item_compra.ItemCompra;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemCompraResponse(
        @NotNull
        Long idItemCompra,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal quantidade,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal itemValue

) {
    public static ItemCompraResponse fromEntity(ItemCompra itemCompra) {
        return new ItemCompraResponse(
                itemCompra.getId(),
                itemCompra.getQuantidade(),
                itemCompra.getItemValue()

        );
    }


}
