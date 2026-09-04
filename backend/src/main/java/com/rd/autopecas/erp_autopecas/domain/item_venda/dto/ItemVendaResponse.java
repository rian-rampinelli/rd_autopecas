package com.rd.autopecas.erp_autopecas.domain.item_venda.dto;

import com.rd.autopecas.erp_autopecas.domain.item_compra.ItemCompra;
import com.rd.autopecas.erp_autopecas.domain.item_venda.ItemVenda;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemVendaResponse(
        @NotNull
        Long idItemVenda,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal quantidade,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal itemValue

) {
    public static ItemVendaResponse fromEntity(ItemVenda itemVenda) {
        return new ItemVendaResponse(
                itemVenda.getId(),
                itemVenda.getQuantidade(),
                itemVenda.getItemValue()

        );
    }


}
