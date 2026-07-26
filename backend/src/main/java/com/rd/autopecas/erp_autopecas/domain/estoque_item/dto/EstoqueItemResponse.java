package com.rd.autopecas.erp_autopecas.domain.estoque_item.dto;

import com.rd.autopecas.erp_autopecas.domain.estoque_item.EstoqueItem;

import java.math.BigDecimal;

public record EstoqueItemResponse(
        Long id,
        BigDecimal quantidade,
        String localizacao


) {
    public static EstoqueItemResponse fromEntity(EstoqueItem estoqueItem) {
        return new EstoqueItemResponse(
                estoqueItem.getId(),
                estoqueItem.getQuantidade(),
                estoqueItem.getLocalizacao()
        );
    }
}
