package com.rd.autopecas.erp_autopecas.domain.carro.dto;

import com.rd.autopecas.erp_autopecas.domain.carro.Carro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CarroRequest(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Motor é obrigatório")
        String motor,
        
        @NotBlank(message = "Marca é obrigatório")
        String marca,

        @NotBlank(message = "Modelo é obrigatório")
        String modelo,

        @NotBlank(message = "Versão é obrigatório")
        String versao,

        @NotNull(message = "Ano de Fabricacao é obrigatório")
        LocalDate anoFabricacao



) {
    public Carro toEntity(){
        Carro carro = new Carro();
        carro.setNome(name);
        carro.setMotor(motor);
        carro.setMarca(marca);
        carro.setVersao(versao);
        carro.setModelo(modelo);
        carro.setAnoFabricacao(anoFabricacao);
        return carro;
    }
}
