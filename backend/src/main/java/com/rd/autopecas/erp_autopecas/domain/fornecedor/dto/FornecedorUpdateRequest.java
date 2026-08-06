package com.rd.autopecas.erp_autopecas.domain.fornecedor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record FornecedorUpdateRequest(
        @Size(min = 2, message = "Nome deve ter no mínimo 2 caracteres")
        String name,
        @Email(message = "Email inválido")
        String email,
        @Size(min = 10, message = "Numero deve ter no mínimo 10 caracteres")
        String numero,
        @Size(min =11 , message = "Cnpj deve ter no mínimo 11 caracteres")
        String cnpj

) {

}
