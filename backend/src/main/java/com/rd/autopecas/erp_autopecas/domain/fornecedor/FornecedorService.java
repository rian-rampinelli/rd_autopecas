package com.rd.autopecas.erp_autopecas.domain.fornecedor;

import com.rd.autopecas.erp_autopecas.domain.fornecedor.dto.FornecedorRequest;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.dto.FornecedorResponse;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.dto.FornecedorUpdateRequest;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorResponse findById(Long id){
        Fornecedor fornecedor = findEntityById(id);
        return(FornecedorResponse.fromEntity(fornecedor));
    }

    public List<FornecedorResponse> findAll(){
        return fornecedorRepository.findAll().stream()
                .map(fornecedor -> FornecedorResponse.fromEntity(fornecedor))
                .toList();
    }

    public FornecedorResponse create(FornecedorRequest fornecedorRequest) {
        Fornecedor fornecedor = fornecedorRequest.toEntity();

        validarEmailDisponivel(fornecedorRequest.email());
        validarCnpjDisponivel(fornecedorRequest.cnpj());

        fornecedorRepository.save(fornecedor);
        return FornecedorResponse.fromEntity(fornecedor);
    }

    public void deleteById(Long id){
        findEntityById(id);
        fornecedorRepository.deleteById(id);
    }

    @Transactional
    public FornecedorResponse update(FornecedorUpdateRequest updateRequest, Long id){
        Fornecedor fornecedor = findEntityById(id);

        if(updateRequest.email() != null){
            validarEmailDisponivel(updateRequest.email());
            fornecedor.setEmail(updateRequest.email());
        }
        if(updateRequest.name() != null){
            fornecedor.setNome(updateRequest.name());
        }
        if(updateRequest.numero() != null){
            fornecedor.setNumero(updateRequest.numero());
        }
        fornecedorRepository.save(fornecedor);
        return FornecedorResponse.fromEntity(fornecedor);
    }

    //helpers
    public Fornecedor findEntityById(Long id){
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
    }

    private void validarEmailDisponivel(String email) {
        if (fornecedorRepository.existsByEmail(email)) {
            throw new AtributeAlredyExistsException("Email já cadastrado");
        }
    }

    private void validarCnpjDisponivel(String cpf) {
        if (fornecedorRepository.existsByCnpj(cpf)) {
            throw new AtributeAlredyExistsException("CPF já cadastrado");
        }
    }
}
