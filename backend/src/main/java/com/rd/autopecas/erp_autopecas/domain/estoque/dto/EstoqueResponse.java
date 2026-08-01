package com.rd.autopecas.erp_autopecas.domain.estoque.dto;

import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;

import java.util.List;

public record EstoqueResponse(
        Long id,
        Long unidadeId,
        String nome,
        String descricao,
        List<EstoqueItemResponse> items

) {
    public static EstoqueResponse fromEntity(Estoque estoque) {
        List<EstoqueItemResponse> itemNoEstoque = estoque.getItemsEstoque().stream()
                .map(estoqueItem -> EstoqueItemResponse.fromEntity(estoqueItem))
                .toList();

        return new EstoqueResponse(
                estoque.getId(),
                estoque.getUnidade().getId(),
                estoque.getNome(),
                estoque.getDescricao(),
                itemNoEstoque
        );
    }
}
