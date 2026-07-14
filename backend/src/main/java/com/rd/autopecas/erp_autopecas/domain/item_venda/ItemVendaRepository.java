package com.rd.autopecas.erp_autopecas.domain.item_venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}