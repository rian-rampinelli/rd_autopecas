package com.rd.autopecas.erp_autopecas.domain.estoque.dto;

import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;


public record EstoqueResumeResponse(
        Long id,
        String nome


) {
    public static EstoqueResumeResponse fromEntity(Estoque estoque) {
        return new EstoqueResumeResponse(
                estoque.getId(),
                estoque.getNome()

        );
    }
}
