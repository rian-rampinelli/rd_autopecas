package com.rd.autopecas.erp_autopecas.domain.fornecedor;

import com.rd.autopecas.erp_autopecas.domain.common.StatusCommon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    boolean existsByEmail(String email);
    boolean existsByCnpj(String cpf);
    boolean existsByNome(String name);
    boolean existsByNumero(String numero);

    //usando jpql,sql + java(bem mais resumido)
    @Query("""
        SELECT f
        FROM Fornecedor f
        WHERE (:status IS NULL OR f.status = :status)
        AND LOWER(f.nome) LIKE LOWER(CONCAT('%', :nome, '%'))
        AND LOWER(f.cnpj) LIKE LOWER(CONCAT('%', :cnpj, '%'))
        AND LOWER(f.numero) LIKE LOWER(CONCAT('%', :numero, '%'))
    """)
    Page<Fornecedor> findAllFornecedores(@Param("nome")  String nome, StatusCommon status,@Param("cnpj")  String cnpj,@Param("numero")  String numero ,Pageable pageable);
}
