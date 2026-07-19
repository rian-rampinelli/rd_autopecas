package com.rd.autopecas.erp_autopecas.domain.cliente.dto;

import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.EnderecoCliente;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteRequest;
import jakarta.validation.constraints.*;
import java.util.List;

public record ClienteRequest(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
        String cpf,

        @NotBlank(message = "Numero é obrigatório")
        String numero,

        List<EnderecoClienteRequest> enderecos
) {
    public Cliente toEntity(){
        Cliente cliente = new Cliente();
        List<EnderecoCliente> enderecoClientes = enderecos().stream()
                .map(dto -> dto.toEntity(cliente))
                .toList();

        cliente.setNome(name);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setNumero(numero);
        cliente.setEnderecoClientes(enderecoClientes);
        return cliente;
    }
}
