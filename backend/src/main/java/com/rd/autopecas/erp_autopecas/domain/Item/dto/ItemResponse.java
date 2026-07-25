package com.rd.autopecas.erp_autopecas.domain.Item.dto;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.carro.Carro;
import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroResponse;
import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteResponse;

import java.math.BigDecimal;
import java.util.List;

public record ItemResponse(
        Long id,
        String name,
        Long codigo,
        String descricao,
        String marca,
        String typeItem,
        BigDecimal standartPrice,
        List<CarroResponse> carros
) {
    public static ItemResponse fromEntity(Item item) {
        List<CarroResponse> carroResponses = item.getCarros().stream()
                .map(carro -> CarroResponse.fromEntity(carro))
                .toList();

        return new ItemResponse(
                item.getId(),
                item.getNome(),
                item.getCodigo(),
                item.getDescricao(),
                item.getMarca(),
                item.getTypeItem(),
                item.getStandartPrice(),
                carroResponses
        );
    }
}
