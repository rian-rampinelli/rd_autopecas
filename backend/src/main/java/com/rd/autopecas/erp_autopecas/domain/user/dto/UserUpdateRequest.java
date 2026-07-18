package com.rd.autopecas.erp_autopecas.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @Size(max = 255, message = "Nome não pode ter mais de 255 caracteres")
        String nome,
        
        @Email(message = "Email deve ser válido")
        @Size(max = 128, message = "Email não pode ter mais de 128 caracteres")
        String email,
        
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password
) {
}
