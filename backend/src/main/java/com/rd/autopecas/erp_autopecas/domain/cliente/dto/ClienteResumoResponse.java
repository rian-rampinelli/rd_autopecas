package com.rd.autopecas.erp_autopecas.domain.cliente.dto;

import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;

public record ClienteResumoResponse(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public static ClienteResumoResponse fromEntity(Cliente cliente) {
        return new ClienteResumoResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf()
        );
    }
}