package com.rd.autopecas.erp_autopecas.domain.compra.dto;



public record CompraRequest(
        Long idFornecedor,
        Long idFuncionario,
        Long idFormaPagamento
//        List<itemCompraRequest> items

) {

}
