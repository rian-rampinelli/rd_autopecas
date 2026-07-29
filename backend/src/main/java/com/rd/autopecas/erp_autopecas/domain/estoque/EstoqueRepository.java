package com.rd.autopecas.erp_autopecas.domain.estoque;

import com.rd.autopecas.erp_autopecas.domain.estoque_item.EstoqueItem;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.MovimentacaoEstoque;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.dto.MovimentacaoEstoqueResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
    """, nativeQuery = true)
    List<EstoqueItemResponse> findAllItemsByEstoque(Long estoqueId);

    //usando slq native
    @Query(value = """
        SELECT me.id,ei.id,i.nome,me.quantidade,me.type_movimentacao
        FROM movimentacao_estoque me
        inner join estoque_item ei
        on me.id_estoque_item = ei.id
        inner join item i
        on ei.id_item = i.id
        WHERE ei.id_estoque = :estoqueId
    """, nativeQuery = true)
    List<MovimentacaoEstoqueResponse> historicoEstoque(Long estoqueId);

    //usando slq native
    @Query(value = """
        SELECT me.id,ei.id,i.nome,me.quantidade,me.type_movimentacao
        FROM movimentacao_estoque me
        inner join estoque_item ei
        on me.id_estoque_item = ei.id
        inner join item i
        on ei.id_item = i.id
        WHERE ei.id_estoque = :estoqueId and ei.id_item = :itemId
    """, nativeQuery = true)
    List<MovimentacaoEstoqueResponse> historicoEstoquePorItem(Long estoqueId,Long itemId);
}