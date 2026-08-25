package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @EntityGraph(attributePaths = {"enderecoFuncionarios","user"})
    Page<Funcionario> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"enderecoFuncionarios","user","user.roles"})
    Optional<Funcionario> findById(Long id);

    Boolean existsByUser_Cpf(String cpf);
    Boolean existsByUser_Email(String email);



    //usei eg pois n filtro por endereco,caso filtrasse,usaria left join para trazer enderecos inves do ea
    //deixo com o service para tranformar em dto,n faço dto projection
    // true or true


    @EntityGraph(attributePaths = "enderecoFuncionarios")
    @Query("""
    SELECT DISTINCT f
    FROM Funcionario f
    JOIN f.user u
    where LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))
    AND LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))
    AND LOWER(u.cpf) LIKE LOWER(CONCAT('%', :cpf, '%'))
    AND LOWER(f.status) LIKE LOWER(CONCAT('%', :status, '%'))
    AND LOWER(f.cargo) LIKE LOWER(CONCAT('%', :cargo, '%'))
    AND (:minSalario IS NULL OR f.salario >= :minSalario)
    AND (:maxSalario IS NULL OR f.salario <= :maxSalario)
    """)
    Page<Funcionario> findAllWithFilter(@Param("nome") String nome, @Param("email") String email, @Param("cpf") String cpf,
                                        @Param("status") String status,@Param("cargo") String cargo, @Param("minSalario") BigDecimal minSalario, @Param("maxSalario") BigDecimal maxSalario, Pageable pageable);



    @Query(value = """
    SELECT r.*
    FROM funcionario f
    JOIN users u ON u.id = f.id_user
    JOIN users_roles ur ON ur.id_user = u.id
    JOIN roles r ON r.id = ur.id_role
    WHERE f.id = :id
    """, nativeQuery = true)
    Set<Role> findRolesByFuncionarioId(@Param("id") Long id);
}

