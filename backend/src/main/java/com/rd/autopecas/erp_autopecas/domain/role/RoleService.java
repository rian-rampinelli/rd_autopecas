package com.rd.autopecas.erp_autopecas.domain.role;

import com.rd.autopecas.erp_autopecas.domain.role.dto.RoleRequest;
import com.rd.autopecas.erp_autopecas.domain.role.dto.RoleResponse;
import com.rd.autopecas.erp_autopecas.domain.role.enums.RoleUser;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleResponse findById(Long id){
        Role role = findEntityRole(id);
        return RoleResponse.fromEntity(role);
    }

    public List<RoleResponse> findAll(){
        return roleRepository.findAll().stream()
                .map(role -> RoleResponse.fromEntity(role))
                .toList();
    }

    public RoleResponse create(RoleRequest roleRequest){
        validaNomeExistente(roleRequest.name());
        Role role = roleRequest.toEntity();
        roleRepository.save(role);
        return RoleResponse.fromEntity(role);
    }

    //helpers
    private Role findEntityRole(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role não encontrada."));
    }

    private void validaNomeExistente(RoleUser name){
        if(roleRepository.existsByName(name)){
            throw new AtributeAlredyExistsException("name ja existe");
        }
    }

}
