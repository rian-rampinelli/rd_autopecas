package com.rd.autopecas.erp_autopecas.domain.cliente.dto;

import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteResponse;


import java.util.List;

public record ClienteResponse(
        Long id,
        String name,
        String email,
        String cpf,
        String numero,
        List<EnderecoClienteResponse> enderecos
) {
    public static ClienteResponse fromEntity(Cliente cliente) {
        List<EnderecoClienteResponse> enderecoResponses = cliente.getEnderecoClientes().stream()
                .map(enderecoCliente -> EnderecoClienteResponse.fromEntity(enderecoCliente))
                .toList();

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getNumero(),
                enderecoResponses
        );
    }
}
