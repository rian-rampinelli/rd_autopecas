package com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto;


import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;

public record FormaPagamentoResponse(
        Long id,
        String name

) {
    public static FormaPagamentoResponse fromEntity(FormaPagamento formaPagamento) {
        return new FormaPagamentoResponse(
                formaPagamento.getId(),
                formaPagamento.getName()
        );
    }
}
