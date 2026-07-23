package com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto;

public record EnderecoClienteUpdateRequest(
        String rua,
        Long numero,
        String cidade,
        String bairro,
        String complemento,
        String cep
) {
}
