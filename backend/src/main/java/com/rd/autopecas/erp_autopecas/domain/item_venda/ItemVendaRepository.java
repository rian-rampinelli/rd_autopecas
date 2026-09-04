package com.rd.autopecas.erp_autopecas.domain.item_venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    Optional<ItemVenda> findByIdAndVenda_Id(Long idItemVenda, Long idVenda);
    Optional<ItemVenda> findByItem_IdAndVenda_Id(Long idItem, Long idVenda);
}