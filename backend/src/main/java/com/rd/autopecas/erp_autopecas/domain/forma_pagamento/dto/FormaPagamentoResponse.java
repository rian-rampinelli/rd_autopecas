package com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto;


import com.rd.autopecas.erp_autopecas.domain.common.StatusCommon;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;

public record FormaPagamentoResponse(
        Long id,
        String name,
        StatusCommon statusCommon

) {
    public static FormaPagamentoResponse fromEntity(FormaPagamento formaPagamento) {
        return new FormaPagamentoResponse(
                formaPagamento.getId(),
                formaPagamento.getName(),
                formaPagamento.getStatus()
        );
    }
}
