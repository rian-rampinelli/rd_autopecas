package com.rd.autopecas.erp_autopecas.domain.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.swing.text.StyledEditorKit;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @EntityGraph(attributePaths = {"enderecoFuncionarios","user"})
    Page<Funcionario> findAll(Pageable pageable);
    @EntityGraph(attributePaths = {"enderecoFuncionarios","user"})
    Optional<Funcionario> findById(Long id);

    Boolean existsByUser_Cpf(String cpf);
    Boolean existsByUser_Nome(String nome);
    Boolean existsByUser_Email(String email);
}