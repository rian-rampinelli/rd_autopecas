package com.rd.autopecas.erp_autopecas.domain.Item.dto;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.carro.Carro;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ItemRequest(

        @NotNull(message = "codigo é obrigatório")
        Long codigo,

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Descrição é obrigatório")
        String descricao,

        @NotBlank(message = "Marca é obrigatório")
        String marca,

        @NotBlank(message = "Tipo é obrigatório")
        String typeItem,

        @NotNull(message = "Preço é obrigatório")
        BigDecimal standardPrice,

        List<Long> idsCarros

) {
    public Item toEntity(){
        Item item = new Item();
        item.setCodigo(codigo);
        item.setTypeItem(typeItem);
        item.setMarca(marca);
        item.setDescricao(descricao);
        item.setStandartPrice(standardPrice);
        item.setNome(name);
        return item;
    }
}
