package com.rd.autopecas.erp_autopecas.domain.role.dto;


import com.rd.autopecas.erp_autopecas.domain.role.Role;
import com.rd.autopecas.erp_autopecas.domain.role.enums.RoleUser;

public record RoleResponse(
        Long id,
        RoleUser name
) {
    public static RoleResponse fromEntity(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getName()

        );
    }
}
