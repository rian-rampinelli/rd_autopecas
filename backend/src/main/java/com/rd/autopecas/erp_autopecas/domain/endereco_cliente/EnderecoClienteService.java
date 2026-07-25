package com.rd.autopecas.erp_autopecas.domain.endereco_cliente;

import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.cliente.ClienteRepository;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteRequest;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteResponse;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteUpdateRequest;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class EnderecoClienteService {

    private final EnderecoClienteRepository enderecoClienteRepository;
    private final ClienteRepository clienteRepository;


    @Transactional
    public EnderecoClienteResponse findById(Long idEndereco, Long idCliente){
        EnderecoCliente enderecoCliente = findByIdAndIdCliente(idEndereco,idCliente);
        return(EnderecoClienteResponse.fromEntity(enderecoCliente));
    }

    @Transactional
    public List<EnderecoClienteResponse> findAll(Long id){
        Cliente cliente = findEntityCliente(id);
        return cliente.getEnderecoClientes().stream()
                .map(enderecoCliente -> EnderecoClienteResponse.fromEntity(enderecoCliente))
                .toList();
    }

    @Transactional
    public EnderecoClienteResponse create(EnderecoClienteRequest enderecoClienteRequest, Long idCliente) {
        Cliente cliente = findEntityCliente(idCliente);
        EnderecoCliente enderecoCliente = enderecoClienteRequest.toEntity();
        cliente.addEndereco(enderecoCliente);
        clienteRepository.save(cliente);
        return EnderecoClienteResponse.fromEntity(enderecoCliente);
    }

    @Transactional
    public void delete(Long idEndereco, Long idCliente){
        EnderecoCliente enderecoCliente = findByIdAndIdCliente(idEndereco,idCliente);
        enderecoClienteRepository.delete(enderecoCliente);
    }

    @Transactional
    public EnderecoClienteResponse update(EnderecoClienteUpdateRequest updateRequest, Long idEndereco,Long idCliente){
        EnderecoCliente enderecoCliente = findByIdAndIdCliente(idEndereco,idCliente);


        if(updateRequest.cep() != null){
            enderecoCliente.setCep(updateRequest.cep());
        }
        if(updateRequest.complemento() != null){
            enderecoCliente.setComplemento(updateRequest.complemento());
        }
        if(updateRequest.rua() != null){
            enderecoCliente.setRua(updateRequest.rua());
        }
        if(updateRequest.bairro() != null){
            enderecoCliente.setBairro(updateRequest.bairro());
        }
        if(updateRequest.numero() != null){
            enderecoCliente.setNumero(updateRequest.numero());
        }
        enderecoClienteRepository.save(enderecoCliente);
        return EnderecoClienteResponse.fromEntity(enderecoCliente);
    }


    //metodos/funções helpers
    private EnderecoCliente findEntityEndereco(Long id){
        EnderecoCliente enderecoCliente = enderecoClienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("endereço não encontrado!"));
        return enderecoCliente;
    }
    public Cliente findEntityCliente(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }


    private EnderecoCliente findByIdAndIdCliente(Long idEndereco,Long idCliente){
        return enderecoClienteRepository.findByIdAndCliente_Id(idEndereco,idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco nao pertence a esse cliente!"));

    }
}
