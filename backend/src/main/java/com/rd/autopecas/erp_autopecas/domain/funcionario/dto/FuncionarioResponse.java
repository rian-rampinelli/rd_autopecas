package com.rd.autopecas.erp_autopecas.domain.funcionario.dto;

import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto.EnderecoFuncionarioResponse;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record FuncionarioResponse(
        Long id,
        String nome,
        String email,
        String cpf,
        StatusFuncionario status,
        String cargo,
        BigDecimal salario,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,
        List<EnderecoFuncionarioResponse> enderecos
) {
    public static FuncionarioResponse fromEntity(Funcionario funcionario){
        List<EnderecoFuncionarioResponse> enderecos = funcionario.getEnderecoFuncionarios().stream()
                .map(enderecoFuncionario -> EnderecoFuncionarioResponse.fromEntity(enderecoFuncionario))
                .toList();

        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getUser().getNome(),
                funcionario.getUser().getEmail(),
                funcionario.getUser().getCpf(),
                funcionario.getStatus(),
                funcionario.getCargo(),
                funcionario.getSalario(),
                funcionario.getCreatedAt(),
                funcionario.getUpdateAt(),
                enderecos
        );

    }
}
