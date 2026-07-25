package com.rd.autopecas.erp_autopecas.domain.estoque.dto;

import com.rd.autopecas.erp_autopecas.domain.fornecedor.Fornecedor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record EstoqueRequest(
        @NotNull
        Long unidadeId
) {

}
