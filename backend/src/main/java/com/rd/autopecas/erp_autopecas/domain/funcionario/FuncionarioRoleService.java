package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.role.Role;
import com.rd.autopecas.erp_autopecas.domain.role.RoleRepository;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FuncionarioRoleService {

    private final FuncionarioRepository funcionarioRepository;
    private final RoleRepository roleRepository;

//    private Funcionario addRole(){
//
//    }
//
//    private Funcionario removerRole(){
//
//    }
//
//    private List<Role> buscarRoles(){
//
//    }

    //helpers
    private Funcionario findFuncionarioById(Long idFuncionario){
        return funcionarioRepository.findById(idFuncionario)
                .orElseThrow(()-> new ResourceNotFoundException("funcionario não encontrado!"));
    }


}
