package com.rd.autopecas.erp_autopecas.domain.estoque_item;

import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface EstoqueItemRepository extends JpaRepository<EstoqueItem, Long> {
    Optional<EstoqueItem> findByEstoque_IdAndItem_Id(Long estoqueId , Long itemId);

    //usando slq native
    @Query(value = """
    SELECT ei.id,e.id,i.id,i.nome,ei.quantidade,ei.localizacao
    FROM estoque_item ei
    INNER JOIN item i 
    ON ei.id_item = i.id
    INNER JOIN estoque e
    ON ei.id_estoque = e.id
    WHERE (:idEstoque IS NULL OR ei.id_estoque = :idEstoque)
    """,
            nativeQuery = true)
    public Page<EstoqueItemResponse> findWithFilters(Pageable pageable, @Param("idEstoque") Long idEstoque);


}