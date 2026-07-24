package com.rd.autopecas.erp_autopecas.domain.unidade.dto;

import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;
import jakarta.validation.constraints.Size;

public record UnidadeUpdateRequest(
        @Size(min = 2, message = "Endereco deve ter no mínimo 5 caracteres")
        String endereco,
        String status

) {

}
