package com.rd.autopecas.erp_autopecas.domain.estoque;

import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.dto.MovimentacaoEstoqueResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByIdAndUnidade_Id(Long idEstoque, Long idUnidade);

    //usando slq native
    @Query(value = """
        SELECT ei.id,i.nome,ei.quantidade,ei.localizacao
        FROM estoque_item ei
        inner join item i
        on ei.id_item = i.id
        WHERE id_estoque = :estoqueId
        and (:item IS NULL OR ei.id_item = :item)
        and (:nomeItem IS NULL OR lower(i.nome) LIKE lower(CONCAT('%', :nomeItem, '%')))
        and (:localizacao IS NULL OR lower(ei.localizacao) LIKE lower(CONCAT('%', :localizacao, '%')))
        and (:qtdMinima IS NULL OR ei.quantidade >= :qtdMinima)
        and (:qtdMaxima IS NULL OR ei.quantidade <= :qtdMaxima)
    """, nativeQuery = true)
    List<EstoqueItemResponse> findAllItemsByEstoque(Long estoqueId,Long item,String nomeItem,String localizacao,BigDecimal qtdMinima,BigDecimal qtdMaxima);

    @Query(value = """
        SELECT me.id,ei.id,i.nome,me.quantidade,me.type_movimentacao
        FROM movimentacao_estoque me
        inner join estoque_item ei
        on me.id_estoque_item = ei.id
        inner join item i
        on ei.id_item = i.id
        WHERE ei.id_estoque = :estoqueId
        and (:item IS NULL OR ei.id_item = :item)
        and (:nomeItem IS NULL OR lower(i.nome) LIKE lower(CONCAT('%', :nomeItem, '%')))
        and (:tipo IS NULL OR me.type_movimentacao = :tipo)
        and (:qtdMinima IS NULL OR me.quantidade >= :qtdMinima)
        and (:qtdMaxima IS NULL OR me.quantidade <= :qtdMaxima)
    """, nativeQuery = true)
    List<MovimentacaoEstoqueResponse> buscarHistoricoEstoque(Long estoqueId, Long item,String nomeItem,String tipo,BigDecimal qtdMinima,BigDecimal qtdMaxima);


}