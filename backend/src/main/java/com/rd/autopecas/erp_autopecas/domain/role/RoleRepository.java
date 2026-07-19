package com.rd.autopecas.erp_autopecas.domain.role;

import com.rd.autopecas.erp_autopecas.domain.role.enums.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleUser name);
}