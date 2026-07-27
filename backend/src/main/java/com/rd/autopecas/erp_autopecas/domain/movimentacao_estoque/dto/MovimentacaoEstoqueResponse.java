package com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.dto;

import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.MovimentacaoEstoque;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.enums.TypeMovimentacao;

import java.math.BigDecimal;

public record MovimentacaoEstoqueResponse(
        Long id,
        Long idItemEstoque,
        String nomeItem,
        BigDecimal quantidadeMovimentada,
        TypeMovimentacao typeMovimentacao


) {
    public static MovimentacaoEstoqueResponse fromEntity(MovimentacaoEstoque movimentacaoEstoque) {
        return new MovimentacaoEstoqueResponse(
                movimentacaoEstoque.getId(),
                movimentacaoEstoque.getEstoqueItem().getId(),
                movimentacaoEstoque.getEstoqueItem().getItem().getNome(),
                movimentacaoEstoque.getQuantidade(),
                movimentacaoEstoque.getTypeMovimentacao()

        );
    }
}
