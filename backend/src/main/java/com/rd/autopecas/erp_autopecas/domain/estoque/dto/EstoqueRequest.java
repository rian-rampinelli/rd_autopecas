package com.rd.autopecas.erp_autopecas.domain.estoque.dto;

import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
public record EstoqueRequest(
        String nome,
        String descricao
) {
    public Estoque toEntity(){
        Estoque estoque = new Estoque();
        estoque.setNome(nome);
        estoque.setDescricao(descricao);
        return estoque;
    }

}
