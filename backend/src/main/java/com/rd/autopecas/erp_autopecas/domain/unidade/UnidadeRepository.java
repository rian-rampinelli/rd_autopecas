package com.rd.autopecas.erp_autopecas.domain.unidade;

import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;
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
    List<Estoque> findAllEstoquesByUnidade(Long unidadeId);

    //usando slq native
    @Query(value = """
        SELECT u  
        FROM Unidade u
        LEFT JOIN FETCH u.estoques
        WHERE u.status = :status
    """)
    List<Unidade> findUnidadesByStatus(StatusUnidade status);

    //usando jpql,sql + java(bem mais resumido)
    @Query("""
    SELECT u  
    FROM Unidade u
    LEFT JOIN FETCH u.estoques
    """)
    List<Unidade> findUnidadesWithEstoques();
}