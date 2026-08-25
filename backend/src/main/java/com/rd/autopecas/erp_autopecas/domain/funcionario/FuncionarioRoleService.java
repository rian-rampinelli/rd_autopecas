package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.funcionario.dto.FuncionarioResponse;
import com.rd.autopecas.erp_autopecas.domain.role.Role;
import com.rd.autopecas.erp_autopecas.domain.role.RoleRepository;
import com.rd.autopecas.erp_autopecas.domain.role.dto.RoleResponse;
import com.rd.autopecas.erp_autopecas.domain.user.UserRepository;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class FuncionarioRoleService {
    private final UserRepository userRepository;

    private final FuncionarioRepository funcionarioRepository;
    private final RoleRepository roleRepository;


    @Transactional
    public FuncionarioResponse addRole(Long idFuncionario, Long idRole){
        log.info("entrei no add role");
        Role role = findEntityRole(idRole);
        Funcionario funcionario = findFuncionarioById(idFuncionario);
        if (funcionario.getUser().getRoles().contains(role)) {
            throw new AtributeAlredyExistsException("Usuário já possui essa role");
        }
        funcionario.getUser().addRole(role);
        funcionarioRepository.save(funcionario);
        return FuncionarioResponse.fromEntity(funcionario);
    }


    @Transactional
    public FuncionarioResponse removerRole(Long idFuncionario, Long idRole){
        log.info("entrei no remove role");
        Role role = findEntityRole(idRole);
        Funcionario funcionario = findFuncionarioById(idFuncionario);
        funcionario.getUser().removeRole(role);
        funcionarioRepository.save(funcionario);
        return FuncionarioResponse.fromEntity(funcionario);
    }


    public Set<RoleResponse> buscarRoles(Long id) {
        return funcionarioRepository.findRolesByFuncionarioId(id).stream()
                .map(role -> RoleResponse.fromEntity(role))
                .collect(Collectors.toSet());
    }

    //helpers
    private Funcionario findFuncionarioById(Long idFuncionario){
        return funcionarioRepository.findById(idFuncionario)
                .orElseThrow(()-> new ResourceNotFoundException("funcionario não encontrado!"));
    }

    private Role findEntityRole(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role não encontrada."));
    }

}
