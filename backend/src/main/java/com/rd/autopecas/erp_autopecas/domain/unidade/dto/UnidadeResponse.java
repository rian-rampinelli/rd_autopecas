package com.rd.autopecas.erp_autopecas.domain.unidade.dto;

import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;
import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;

import java.util.List;

public record UnidadeResponse(
        Long id,
        String endereco,
        String status


) {
    public static UnidadeResponse fromEntity(Unidade unidade) {
        return new UnidadeResponse(
                unidade.getId(),
                unidade.getEndereco(),
                unidade.getStatus().name()
        );
    }
}
