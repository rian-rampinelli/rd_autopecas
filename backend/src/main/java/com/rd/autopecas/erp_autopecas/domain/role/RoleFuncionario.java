package com.rd.autopecas.erp_autopecas.domain.role;

import lombok.Getter;

@Getter
public enum RoleFuncionario {
    ROLE_VENDEDOR(1L),
    ROLE_GERENTE(2L),
    ROLE_ESTOQUISTA(3L),
    ROLE_ADMINISTRADOR(4L);

    Long roleId;

    RoleFuncionario(Long roleId){
        this.roleId = roleId;
    }
}
