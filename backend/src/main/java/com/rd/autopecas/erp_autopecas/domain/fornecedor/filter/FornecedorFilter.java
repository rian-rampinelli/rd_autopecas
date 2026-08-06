package com.rd.autopecas.erp_autopecas.domain.fornecedor.filter;
public record FornecedorFilter(
        String nome,
        String status,
        String cnpj,
        String numero
) {
}
