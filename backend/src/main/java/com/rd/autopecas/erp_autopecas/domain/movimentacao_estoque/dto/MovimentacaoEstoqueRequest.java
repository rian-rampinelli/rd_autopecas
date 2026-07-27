package com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.dto;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.MovimentacaoEstoque;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.enums.TypeMovimentacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MovimentacaoEstoqueRequest(
        @NotNull
        Long idItemEstoque,
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal quantidade,
        @NotNull
        String typeMovimentacao
) {
    public MovimentacaoEstoque toEntity(){
        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setQuantidade(quantidade);
        movimentacaoEstoque.setTypeMovimentacao(TypeMovimentacao.valueOf(typeMovimentacao));
        return movimentacaoEstoque;
    }

}
