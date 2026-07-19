package com.rd.autopecas.erp_autopecas.domain.endereco_funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoFuncionarioRepository extends JpaRepository<EnderecoFuncionario, Long> {
}