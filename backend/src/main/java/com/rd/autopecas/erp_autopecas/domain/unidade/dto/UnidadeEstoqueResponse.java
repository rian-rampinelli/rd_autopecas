package com.rd.autopecas.erp_autopecas.domain.unidade.dto;


import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResumeResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;
import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;

import java.util.List;

public record UnidadeEstoqueResponse(
        Long unidadeId,
        String endereco,
        StatusUnidade status,
        List<EstoqueResumeResponse> estoques


) {
    public static UnidadeEstoqueResponse fromEntity(Unidade unidade) {
        List<EstoqueResumeResponse> estoques =
                unidade.getEstoques()
                        .stream()
                        .map(EstoqueResumeResponse::fromEntity)
                        .toList();

        return new UnidadeEstoqueResponse(
                unidade.getId(),
                unidade.getEndereco(),
                unidade.getStatus(),
                estoques

        );
    }


}
