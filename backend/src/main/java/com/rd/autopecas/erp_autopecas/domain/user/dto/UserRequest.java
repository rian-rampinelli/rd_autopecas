package com.rd.autopecas.erp_autopecas.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 255, message = "Nome não pode ter mais de 255 caracteres")
        String nome,
        
        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Email deve ser válido")
        @Size(max = 128, message = "Email não pode ter mais de 128 caracteres")
        String email,
        
        @NotBlank(message = "Senha não pode ser vazia")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password,
        
        @NotBlank(message = "CPF não pode ser vazio")
        @Size(min = 11, max = 11, message = "CPF deve conter exatamente 11 dígitos")
        String cpf
) {
}
