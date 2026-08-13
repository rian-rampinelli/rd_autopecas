package com.rd.autopecas.erp_autopecas.domain.cliente.filter;
public record ClienteFilter(
        String nome,
        String email,
        String cpf,
        String numero
) {
}
