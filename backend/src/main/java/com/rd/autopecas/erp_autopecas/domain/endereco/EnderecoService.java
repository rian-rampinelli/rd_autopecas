package com.rd.autopecas.erp_autopecas.domain.endereco;

import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    //metodos/funções helpers
    private Endereco findById(Long id){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("endereço não encontrado!"));
        return endereco;
    }
}
