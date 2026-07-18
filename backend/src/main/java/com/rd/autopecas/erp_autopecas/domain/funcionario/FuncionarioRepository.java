package com.rd.autopecas.erp_autopecas.domain.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByUser_Nome(String email);
    Boolean existsByUser_Email(String email);
}