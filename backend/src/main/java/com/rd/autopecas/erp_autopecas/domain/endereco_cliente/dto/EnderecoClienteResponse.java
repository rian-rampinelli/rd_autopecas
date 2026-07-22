package com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto;

import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.EnderecoCliente;

public record EnderecoClienteResponse(
        String rua,
        Long numero,
        String cidade,
        String bairro,
        String complemento,
        String cep
) {
    public static EnderecoClienteResponse fromEntity(EnderecoCliente enderecoCliente){
        return new EnderecoClienteResponse(
                enderecoCliente.getRua(),
                enderecoCliente.getNumero(),
                enderecoCliente.getCidade(),
                enderecoCliente.getBairro(),
                enderecoCliente.getComplemento(),
                enderecoCliente.getCep()
        );
    }
}
