package com.rd.autopecas.erp_autopecas.domain.cliente;


import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByNumero(String cpf);

    @EntityGraph(attributePaths = "enderecoClientes")
    @Query("""
    SELECT DISTINCT c
    FROM Cliente c
    JOIN c.enderecoClientes e
    where LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))
    AND LOWER(c.email) LIKE LOWER(CONCAT('%', :email, '%'))
    AND LOWER(c.cpf) LIKE LOWER(CONCAT('%', :cpf, '%'))
    AND LOWER(c.numero) LIKE LOWER(CONCAT('%', :numero, '%'))
    """)
    Page<Cliente> findAllWithFilter(@Param("nome") String nome,@Param("email") String email, @Param("cpf") String cpf, @Param("numero") String numero,Pageable pageable);
}