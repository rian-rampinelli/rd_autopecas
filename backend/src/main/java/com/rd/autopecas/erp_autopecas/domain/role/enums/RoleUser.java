package com.rd.autopecas.erp_autopecas.domain.role.enums;

import lombok.Getter;

@Getter
public enum RoleUser {
    ROLE_VENDEDOR(1L),
    ROLE_RH(2L),
    ROLE_ESTOQUISTA(3L),
    ROLE_ADMIN(4L);

    Long roleId;

    RoleUser(Long roleId){
        this.roleId = roleId;
    }
}
