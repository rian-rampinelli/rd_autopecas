package com.rd.autopecas.erp_autopecas.domain.Item.dto;

import java.util.List;

public record ListCarrosRequest(
        List<Long> idsCarros
) {
}
