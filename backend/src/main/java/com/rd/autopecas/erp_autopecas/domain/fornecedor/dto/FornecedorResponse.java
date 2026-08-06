package com.rd.autopecas.erp_autopecas.domain.fornecedor.dto;

import com.rd.autopecas.erp_autopecas.domain.common.StatusCommon;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.Fornecedor;

public record FornecedorResponse(
        Long id,
        String name,
        String email,
        String cnpj,
        String numero,
        StatusCommon status
) {
    public static FornecedorResponse fromEntity(Fornecedor fornecedor) {
        return new FornecedorResponse(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getEmail(),
                fornecedor.getCnpj(),
                fornecedor.getNumero(),
                fornecedor.getStatus()

        );
    }
}
