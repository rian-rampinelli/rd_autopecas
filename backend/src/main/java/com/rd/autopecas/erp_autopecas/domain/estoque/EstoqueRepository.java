package com.rd.autopecas.erp_autopecas.domain.estoque;

import com.rd.autopecas.erp_autopecas.domain.estoque_item.EstoqueItem;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
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
}