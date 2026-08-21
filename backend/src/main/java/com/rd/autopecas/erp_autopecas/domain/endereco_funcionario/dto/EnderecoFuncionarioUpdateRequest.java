package com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto;

import jakarta.validation.constraints.Pattern;

public record EnderecoFuncionarioUpdateRequest(
        String rua,
        Long numero,
        String cidade,
        String bairro,
        String complemento,
        String cep
) {
}
