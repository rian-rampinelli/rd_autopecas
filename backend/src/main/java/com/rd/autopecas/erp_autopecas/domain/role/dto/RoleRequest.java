package com.rd.autopecas.erp_autopecas.domain.role.dto;

import com.rd.autopecas.erp_autopecas.domain.role.Role;
import com.rd.autopecas.erp_autopecas.domain.role.enums.RoleUser;
import jakarta.validation.constraints.NotBlank;


public record RoleRequest(
        @NotBlank(message = "Nome é obrigatório")
        RoleUser name

) {
    public Role toEntity(){
        Role role = new Role();
        role.setName(name);
        return role;
    }
}
