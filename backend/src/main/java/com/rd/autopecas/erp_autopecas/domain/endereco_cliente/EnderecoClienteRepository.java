package com.rd.autopecas.erp_autopecas.domain.endereco_cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long> {
}