package com.rd.autopecas.erp_autopecas.domain.item_compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {
}