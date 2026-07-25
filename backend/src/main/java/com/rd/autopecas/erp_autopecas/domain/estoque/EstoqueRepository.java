package com.rd.autopecas.erp_autopecas.domain.estoque;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}