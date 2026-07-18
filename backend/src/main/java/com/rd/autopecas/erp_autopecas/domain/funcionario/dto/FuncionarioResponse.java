package com.rd.autopecas.erp_autopecas.domain.funcionario.dto;

import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.domain.role.Role;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record FuncionarioResponse(
        Long id,
        String nomeUser,
        String emailUser,
        String cpfUser,
        StatusFuncionario status,
        BigDecimal salario,
        Set<Role> roles,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
