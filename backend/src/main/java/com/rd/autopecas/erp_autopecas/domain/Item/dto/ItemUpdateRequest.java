package com.rd.autopecas.erp_autopecas.domain.Item.dto;

import java.math.BigDecimal;

public record ItemUpdateRequest(
        Long codigo,
        String name,
        String descricao,
        String marca,
        String typeItem,
        BigDecimal standartPrice
) {

}
