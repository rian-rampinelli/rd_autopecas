package com.rd.autopecas.erp_autopecas.domain.carro.dto;

import com.rd.autopecas.erp_autopecas.domain.carro.Carro;

import java.time.LocalDate;

public record CarroResponse(
        Long id,
        String name,
        String motor,
        String marca,
        String modelo,
        String versao,
        LocalDate anoFabricacao
) {
    public static CarroResponse fromEntity(Carro carro) {
        return new CarroResponse(
                carro.getId(),
                carro.getNome(),
                carro.getMotor(),
                carro.getMarca(),
                carro.getModelo(),
                carro.getVersao(),
                carro.getAnoFabricacao()
        );
    }
}
