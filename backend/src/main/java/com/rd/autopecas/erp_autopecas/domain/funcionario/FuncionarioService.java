package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.funcionario.dto.FuncionarioResponse;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioResponse findById(Long idFuncionario){
        Funcionario funcionario = findFuncionarioById(idFuncionario);
        return FuncionarioResponse.fromEntity(funcionario);
    }

    @Transactional
    public Page<FuncionarioResponse> findAll(Pageable pageable){
        log.info("Entrou no findAll de funcionário");
        return funcionarioRepository.findAll(pageable).map(funcionario -> FuncionarioResponse.fromEntity(funcionario));
    }

    public FuncionarioResponse changeStatus(Long id,String status){
        Funcionario funcionario = findFuncionarioById(id);
        if (funcionario.getStatus().equals(tranformEnum(status))){
            throw new AtributeAlredyExistsException("funcionario ja possui esse status");
        }
        funcionario.setStatus(tranformEnum(status));
        funcionarioRepository.save(funcionario);
        return FuncionarioResponse.fromEntity(funcionario);
    }

    //helpers
    private Funcionario findFuncionarioById(Long idFuncionario){
        return funcionarioRepository.findById(idFuncionario)
                .orElseThrow(()-> new ResourceNotFoundException("funcionario não encontrado!"));
    }

    private StatusFuncionario tranformEnum(String status){
        return StatusFuncionario.valueOf(status.toUpperCase());
    }
}
