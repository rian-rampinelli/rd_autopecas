package com.rd.autopecas.erp_autopecas.domain.unidade.dto;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;


import java.util.List;

public record UnidadeResponse(
        Long id,
        String endereco,
        String status,
        List<EstoqueResponse> estoques


) {
    public static UnidadeResponse fromEntity(Unidade unidade) {
        List<EstoqueResponse> estoques = unidade.getEstoques().stream()
                .map(estoque -> EstoqueResponse.fromEntity(estoque))
                .toList();
        return new UnidadeResponse(
                unidade.getId(),
                unidade.getEndereco(),
                unidade.getStatus().name(),
                estoques
        );
    }
}
