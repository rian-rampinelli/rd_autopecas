package com.rd.autopecas.erp_autopecas.domain.endereco_funcionario;

import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoFuncionarioService {

    private final EnderecoFuncionarioRepository enderecoFuncionarioRepository;

    //metodos/funções helpers
    private EnderecoFuncionario findById(Long id){
        EnderecoFuncionario enderecoFuncionario = enderecoFuncionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("endereço não encontrado!"));
        return enderecoFuncionario;
    }
}
