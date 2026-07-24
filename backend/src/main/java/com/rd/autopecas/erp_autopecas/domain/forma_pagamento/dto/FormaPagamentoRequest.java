package com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto;

import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;
import jakarta.validation.constraints.NotBlank;

public record FormaPagamentoRequest(
        @NotBlank(message = "Nome é obrigatório")
        String name
) {
    public FormaPagamento toEntity(){
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setName(name);
        return formaPagamento;
    }
}
