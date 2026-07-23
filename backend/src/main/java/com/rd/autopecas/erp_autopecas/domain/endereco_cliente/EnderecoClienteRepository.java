package com.rd.autopecas.erp_autopecas.domain.endereco_cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long> {
    Optional<EnderecoCliente>  findByIdAndCliente_Id(Long idEndereco, Long idCliente);
}