package com.rd.autopecas.erp_autopecas.domain.carro.dto;

public record CarroUpdateRequest(
        String name,
        String motor,
        String marca,
        String modelo,
        String versao
) {

}
