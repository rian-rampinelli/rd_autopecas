package com.rd.autopecas.erp_autopecas.domain.endereco_funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoFuncionarioRepository extends JpaRepository<EnderecoFuncionario, Long> {

    Optional<EnderecoFuncionario> findByIdAndFuncionario_Id(Long idEndereco, Long idFuncionario);
    boolean existsByFuncionarioIdAndCepAndCidadeAndBairroAndRuaAndNumeroAndComplemento(
            Long funcionarioId,
            String cep,
            String cidade,
            String bairro,
            String rua,
            Long numero,
            String complemento
    );
}