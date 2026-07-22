package com.rd.autopecas.erp_autopecas.domain.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record ClienteUpdateRequest(
        @Size(min = 2, message = "Nome deve ter no mínimo 2 caracteres")
        String name,
        @Email(message = "Email inválido")
        String email,
        @Size(min = 10, message = "Numero deve ter no mínimo 10 caracteres")
        String numero

) {

}
