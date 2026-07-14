package com.rd.autopecas.erp_autopecas.domain.item_estoque;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Long> {
    Boolean findByItemIdAndEstoqueId(Long itemId, Long estoqueId);
}