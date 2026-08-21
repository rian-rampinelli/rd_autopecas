package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.funcionario.dto.FuncionarioResponse;
import com.rd.autopecas.erp_autopecas.domain.funcionario.dto.FuncionarioUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import com.rd.autopecas.erp_autopecas.exceptions.ValidationException;
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
        log.info("Entrou no findbyid de funcionário");
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

    @Transactional
    public FuncionarioResponse update(Long id, FuncionarioUpdateRequest updateRequest){
        Funcionario funcionario = findFuncionarioById(id);
        if(updateRequest.salary() != null){
            funcionario.setSalario(updateRequest.salary());
        }
        if(updateRequest.cargo() != null){
            funcionario.setCargo(updateRequest.cargo());
        }
        if (updateRequest.cpf() != null){
            validaCpf(updateRequest.cpf());
            funcionario.getUser().setCpf(updateRequest.cpf());
        }
        if (updateRequest.email() != null){
            validaEmail(updateRequest.email());
            funcionario.getUser().setEmail(updateRequest.email());
        }
        if (updateRequest.nome() != null){
            funcionario.getUser().setNome(updateRequest.nome());
        }

        return FuncionarioResponse.fromEntity(funcionario);
    }

    //helpers
    private Funcionario findFuncionarioById(Long idFuncionario){
        return funcionarioRepository.findById(idFuncionario)
                .orElseThrow(()-> new ResourceNotFoundException("funcionario não encontrado!"));
    }

    private void validaCpf(String cpf){
        if(funcionarioRepository.existsByUser_Cpf(cpf)){
            throw new AtributeAlredyExistsException("cpf ja existe");
        }
    }

    private void validaEmail(String email){
        if(funcionarioRepository.existsByUser_Email(email)){
            throw new AtributeAlredyExistsException("email ja existe");
        }
    }


    private StatusFuncionario tranformEnum(String status){
        return StatusFuncionario.valueOf(status.toUpperCase());
    }

}
