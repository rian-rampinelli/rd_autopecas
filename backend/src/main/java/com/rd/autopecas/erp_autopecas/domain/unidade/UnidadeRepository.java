package com.rd.autopecas.erp_autopecas.domain.unidade;

import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
        SELECT DISTINCT u
        FROM Unidade u
        LEFT JOIN FETCH u.estoques
        WHERE (:status IS NULL OR u.status = :status)
    """)
    Page<Unidade> findUnidadesWithEstoques(StatusUnidade status, Pageable pageable);
}