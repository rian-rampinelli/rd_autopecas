package com.rd.autopecas.erp_autopecas.domain.endereco_cliente;

import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoClienteService {

    private final EnderecoClienteRepository enderecoClienteRepository;

    //metodos/funções helpers
    private EnderecoCliente findById(Long id){
        EnderecoCliente enderecoCliente = enderecoClienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("endereço não encontrado!"));
        return enderecoCliente;
    }
}
