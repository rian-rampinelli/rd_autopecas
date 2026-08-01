package com.rd.autopecas.erp_autopecas.domain.unidade;

import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeEstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    //usando slq native
    @Query(value = """
        SELECT *
        FROM estoque
        WHERE id_unidade = :unidadeId
    """, nativeQuery = true)
    Page<Estoque> findAllEstoquesByUnidade(Long unidadeId,Pageable pageable);

    //usando jpql,sql + java(bem mais resumido)
    @Query("""
    SELECT u
    FROM Unidade u
    WHERE (:status IS NULL OR u.status = :status)
    """)
    Page<Unidade> findUnidadesWithEstoques(StatusUnidade status, Pageable pageable);
}