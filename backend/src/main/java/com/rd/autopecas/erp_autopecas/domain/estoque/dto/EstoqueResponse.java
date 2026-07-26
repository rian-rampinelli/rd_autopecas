package com.rd.autopecas.erp_autopecas.domain.estoque.dto;

import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;

public record EstoqueResponse(
        Long id,
        String nome,
        String descricao

) {
    public static EstoqueResponse fromEntity(Estoque estoque) {
        return new EstoqueResponse(
                estoque.getId(),
                estoque.getNome(),
                estoque.getDescricao()
        );
    }
}
