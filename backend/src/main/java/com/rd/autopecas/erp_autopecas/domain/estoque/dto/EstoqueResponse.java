package com.rd.autopecas.erp_autopecas.domain.estoque.dto;

import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;


public record EstoqueResponse(
        Long id

) {
    public static EstoqueResponse fromEntity(Estoque estoque) {
        return new EstoqueResponse(
                estoque.getId()
        );
    }
}
