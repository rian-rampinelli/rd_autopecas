package com.rd.autopecas.erp_autopecas.domain.venda;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @EntityGraph(attributePaths = "ItemsVenda")
    Optional<Venda> findById(Long id);
}