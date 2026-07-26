package com.rd.autopecas.erp_autopecas.domain.estoque_item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueItemRepository extends JpaRepository<EstoqueItem, Long> {
    Optional<EstoqueItem> findByEstoque_IdAndItem_Id(Long estoqueId , Long itemId);
}