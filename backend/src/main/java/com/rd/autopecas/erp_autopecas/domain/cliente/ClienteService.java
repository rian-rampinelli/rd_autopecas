package com.rd.autopecas.erp_autopecas.domain.cliente;

import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteRequest;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteResponse;
import com.rd.autopecas.erp_autopecas.domain.common.StatusCommon;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    //criar end point allenderecos
    //resolver n + 1 e filter de findallclientes
    //n seria melhor colocar o create/delete e upsate de endereco em cliente?

    @Transactional
    public ClienteResponse findById(Long id){
        Cliente cliente = findEntityCliente(id);
        return(ClienteResponse.fromEntity(cliente));
    }

    @Transactional
    public Page<ClienteResponse> findAll(Pageable pageable){
        return clienteRepository.findAll(pageable)
                .map(cliente -> ClienteResponse.fromEntity(cliente));
    }

//    public Page<EnderecoClienteResponse> findAllEnderecos(Long idCliente){
//        Cliente cliente = findEntityCliente(idCliente);
//
//        return cliente.getEnderecoClientes()
//                .map(enderecoCliente -> EnderecoClienteResponse.fromEntity(enderecoCliente))
//                ;
//    }

    public ClienteResponse create(ClienteRequest clienteRequest) {
        alreadyExists(clienteRequest.cpf(), clienteRequest.email(),clienteRequest.numero());
        Cliente cliente = clienteRequest.toEntity();
        cliente.setStatus(StatusCommon.ATIVO);
        clienteRepository.save(cliente);
        return ClienteResponse.fromEntity(cliente);
    }

    public void deactivate(Long id){
        Cliente cliente = findEntityCliente(id);
        verificarStatusDesativo(cliente);
        cliente.setStatus(StatusCommon.DESATIVO);
        clienteRepository.save(cliente);
    }

    public void active(Long id){
        Cliente cliente = findEntityCliente(id);
        verificarStatusAtivo(cliente);
        cliente.setStatus(StatusCommon.ATIVO);
        clienteRepository.save(cliente);
    }

    @Transactional
    public ClienteResponse update(ClienteUpdateRequest updateRequest, Long id){
        alreadyExists(updateRequest.cpf(), updateRequest.email(), updateRequest.name());
        Cliente cliente = findEntityCliente(id);
        if(updateRequest.email() != null){
            cliente.setEmail(updateRequest.email());
        }
        if(updateRequest.name() != null){
            cliente.setNome(updateRequest.name());
        }
        if(updateRequest.numero() != null){
            cliente.setNumero(updateRequest.numero());
        }
        if(updateRequest.cpf() != null){
            cliente.setCpf(updateRequest.cpf());
        }
        clienteRepository.save(cliente);
        return ClienteResponse.fromEntity(cliente);
    }

    //helpers
    public Cliente findEntityCliente(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    private void validarEmailDisponivel(String email) {
        if (clienteRepository.existsByEmail(email)) {
            throw new AtributeAlredyExistsException("Email já cadastrado");
        }
    }

    private void validarCpfDisponivel(String cpf) {
        if (clienteRepository.existsByCpf(cpf)) {
            throw new AtributeAlredyExistsException("CPF já cadastrado");
        }
    }

    private void validarNumeroDisponivel(String numero) {
        if (clienteRepository.existsByNumero(numero)) {
            throw new AtributeAlredyExistsException("numero já cadastrado");
        }
    }

    private void alreadyExists(String cpf,String email,String numero){
        validarCpfDisponivel(cpf);
        validarEmailDisponivel(email);
        validarNumeroDisponivel(numero);
    }

    private void verificarStatusDesativo(Cliente cliente){
        if(cliente.getStatus() == StatusCommon.DESATIVO){
            throw new AtributeAlredyExistsException("cliente ja desativo!");
        }
    }

    private void verificarStatusAtivo(Cliente cliente){
        if(cliente.getStatus() == StatusCommon.ATIVO){
            throw new AtributeAlredyExistsException("cliente ja ativo!");
        }
    }

    private StatusCommon parseStatus(String status) {
        return status == null ? null : StatusCommon.valueOf(status.toUpperCase());
    }

    private String normalize(String value) {
        return value == null ? "" : value;
    }
}
