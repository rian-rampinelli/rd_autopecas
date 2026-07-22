package com.rd.autopecas.erp_autopecas.domain.cliente;

import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteRequest;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteResponse;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteResumoResponse;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponse findById(Long id){
        Cliente cliente = findEntityCliente(id);
        return(ClienteResponse.fromEntity(cliente));
    }

    public List<ClienteResumoResponse> findAll(){
        return clienteRepository.findAll().stream()
                .map(cliente -> ClienteResumoResponse.fromEntity(cliente))
                .toList();
    }

    public ClienteResponse create(ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.toEntity();

        validarEmailDisponivel(clienteRequest.email());
        validarCpfDisponivel(clienteRequest.cpf());

        clienteRepository.save(cliente);
        return ClienteResponse.fromEntity(cliente);
    }

    public void deleteById(Long id){
        findEntityCliente(id);
        clienteRepository.deleteById(id);
    }

    @Transactional
    public ClienteResponse update(ClienteUpdateRequest updateRequest, Long id){
        Cliente cliente = findEntityCliente(id);

        if(updateRequest.email() != null){
            validarEmailDisponivel(updateRequest.email());
            cliente.setEmail(updateRequest.email());
        }
        if(updateRequest.name() != null){
            cliente.setNome(updateRequest.name());
        }
        if(updateRequest.numero() != null){
            cliente.setNumero(updateRequest.numero());
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
}
