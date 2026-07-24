package com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto;

import jakarta.validation.constraints.Size;

public record FormaPagamentoUpdateRequest(
        @Size(min = 2, message = "Nome deve ter no mínimo 2 caracteres")
        String name
) {

}
