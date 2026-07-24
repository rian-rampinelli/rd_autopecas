package com.rd.autopecas.erp_autopecas.domain.fornecedor.dto;

import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.Fornecedor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record FornecedorRequest(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "CNPJ é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CNPJ deve conter 11 dígitos")
        String cnpj,

        @NotBlank(message = "Numero é obrigatório")
        String numero


) {
    public Fornecedor toEntity(){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(name);
        fornecedor.setEmail(email);
        fornecedor.setCnpj(cnpj);
        fornecedor.setNumero(numero);
        return fornecedor;
    }
}
