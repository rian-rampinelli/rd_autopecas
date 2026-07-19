package com.rd.autopecas.erp_autopecas.domain.user.dto;

import com.rd.autopecas.erp_autopecas.domain.role.Role;
import com.rd.autopecas.erp_autopecas.domain.user.User;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(
        Long id,
        String nome,
        String email,
        String cpf,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
    public static UserResponse fromEntity(User user){
        return new UserResponse(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdateAt()
        );
    }
}
