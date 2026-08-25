package com.rd.autopecas.erp_autopecas.domain.role;

import com.rd.autopecas.erp_autopecas.domain.role.enums.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByName(RoleUser name);
}