package com.rd.autopecas.erp_autopecas.domain.item_estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Long> {
    Boolean findByItemIdAndEstoqueId(Long itemId, Long estoqueId);
}