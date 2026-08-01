package com.rd.autopecas.erp_autopecas.domain.Item.dto;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroResponse;

import java.math.BigDecimal;


public record ItemResumeResponse(
        Long id,
        String name,
        Long codigo,
        String descricao,
        String marca,
        String typeItem,
        BigDecimal standartPrice

) {
    public static ItemResumeResponse fromEntity(Item item) {
        return new ItemResumeResponse(
                item.getId(),
                item.getNome(),
                item.getCodigo(),
                item.getDescricao(),
                item.getMarca(),
                item.getTypeItem(),
                item.getStandartPrice()
        );
    }
}
