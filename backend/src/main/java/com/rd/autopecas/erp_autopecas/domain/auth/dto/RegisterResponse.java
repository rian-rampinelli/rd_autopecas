package com.rd.autopecas.erp_autopecas.domain.auth.dto;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.user.User;


import java.math.BigDecimal;

public record RegisterResponse(
        String userName,
        String email,
        String cpf,
        BigDecimal salario
) {
    public static RegisterResponse fromEntity(User user, Funcionario funcionario){
        return new RegisterResponse(
                user.getNome(),
                user.getEmail(),
                user.getCpf(),
                funcionario.getSalario()
        );
    }
}
