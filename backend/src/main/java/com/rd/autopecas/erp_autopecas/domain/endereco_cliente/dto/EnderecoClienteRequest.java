package com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto;

import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.EnderecoCliente;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoClienteRequest(
        @NotBlank
        String rua,
        @NotNull
        Long numero,
        @NotBlank
        String cidade,
        @NotBlank
        String bairro,
        @NotBlank
        String complemento,
        @NotBlank
        String cep
) {
    public EnderecoCliente toEntity(Cliente cliente){
        EnderecoCliente enderecoCliente = new EnderecoCliente();
        enderecoCliente.setRua(this.rua());
        enderecoCliente.setCep(this.cep());
        enderecoCliente.setNumero(this.numero());
        enderecoCliente.setCidade(this.cidade());
        enderecoCliente.setBairro(this.bairro());
        enderecoCliente.setComplemento(this.complemento());
        enderecoCliente.setCliente(cliente);
        return enderecoCliente;
    }
}
