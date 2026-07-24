package com.rd.autopecas.erp_autopecas.domain.unidade.dto;

import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;
import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;
import jakarta.validation.constraints.NotBlank;

public record UnidadeRequest(

        @NotBlank(message = "Endereco é obrigatório")
        String endereco,

        @NotBlank(message = "Status é obrigatório")
        String status

    
) {
    public Unidade toEntity(){
        Unidade unidade = new Unidade();
        unidade.setEndereco(endereco);
        unidade.setStatus(StatusUnidade.valueOf(status.toUpperCase()));
        return unidade;
    }
}
