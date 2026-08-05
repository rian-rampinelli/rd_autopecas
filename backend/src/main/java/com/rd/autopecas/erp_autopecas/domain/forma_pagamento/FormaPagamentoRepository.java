package com.rd.autopecas.erp_autopecas.domain.forma_pagamento;

import com.rd.autopecas.erp_autopecas.domain.common.StatusCommon;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;
import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
    boolean existsByName(String name);

    //usando jpql,sql + java(bem mais resumido)
    @Query("""
        SELECT fp
        FROM FormaPagamento fp
        WHERE (:status IS NULL OR fp.status = :status)
        AND LOWER(fp.name) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    Page<FormaPagamento> findAllFormaPagamentos(@Param("name")  String name, StatusCommon status, Pageable pageable);
}