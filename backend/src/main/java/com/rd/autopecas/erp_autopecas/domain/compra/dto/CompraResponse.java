package com.rd.autopecas.erp_autopecas.domain.compra.dto;

import com.rd.autopecas.erp_autopecas.domain.common.StatusTransacao;
import com.rd.autopecas.erp_autopecas.domain.compra.Compra;



public record CompraResponse(
        Long id,
        StatusTransacao status

) {
    public static CompraResponse fromEntity(Compra compra) {
        return new CompraResponse(
                compra.getId(),
                compra.getStatus()
        );
    }
}
