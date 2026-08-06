package com.rd.autopecas.erp_autopecas.domain.fornecedor;

import com.rd.autopecas.erp_autopecas.domain.common.StatusCommon;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.dto.FornecedorRequest;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.dto.FornecedorResponse;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.dto.FornecedorUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.filter.FornecedorFilter;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorResponse findById(Long id){
        Fornecedor fornecedor = findEntityById(id);
        return(FornecedorResponse.fromEntity(fornecedor));
    }

    public Page<FornecedorResponse> findAll(FornecedorFilter filter, Pageable pageable){
        //trata tipagem para banco de dados
        StatusCommon status = parseStatus(filter.status());
        String nome = normalize(filter.nome());
        String cnpj = normalize(filter.cnpj());
        String numero = normalize(filter.numero());

        Page<Fornecedor> fornecedores = fornecedorRepository.findAllFornecedores(nome,status,cnpj,numero,pageable);
        return fornecedores.map(fornecedor -> FornecedorResponse.fromEntity(fornecedor));
    }

    public FornecedorResponse create(FornecedorRequest fornecedorRequest) {
        Fornecedor fornecedor = fornecedorRequest.toEntity();
        alreadyExists(fornecedorRequest.name(),fornecedorRequest.cnpj(), fornecedorRequest.email(),fornecedorRequest.numero());
        fornecedor.setStatus(StatusCommon.ATIVO);
        fornecedorRepository.save(fornecedor);
        return FornecedorResponse.fromEntity(fornecedor);
    }

    public void deactivate(Long id){
        Fornecedor fornecedor = findEntityById(id);
        verificarStatusDesativo(fornecedor);
        fornecedor.setStatus(StatusCommon.DESATIVO);
        fornecedorRepository.save(fornecedor);
    }

    public void active(Long id){
        Fornecedor fornecedor = findEntityById(id);
        verificarStatusAtivo(fornecedor);
        fornecedor.setStatus(StatusCommon.ATIVO);
        fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public FornecedorResponse update(FornecedorUpdateRequest updateRequest, Long id){
        Fornecedor fornecedor = findEntityById(id);
        alreadyExists(updateRequest.name(),updateRequest.cnpj(),updateRequest.email(), updateRequest.numero());
        if(updateRequest.email() != null){
            fornecedor.setEmail(updateRequest.email());
        }
        if(updateRequest.name() != null){
            fornecedor.setNome(updateRequest.name());
        }
        if(updateRequest.numero() != null){
            fornecedor.setNumero(updateRequest.numero());
        }
        if(updateRequest.cnpj() != null){
            fornecedor.setCnpj(updateRequest.cnpj());
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

    private void validarCnpjDisponivel(String cnpj) {
        if (fornecedorRepository.existsByCnpj(cnpj)) {
            throw new AtributeAlredyExistsException("CNPJ já cadastrado");
        }
    }
    private void validarNomeDisponivel(String nome) {
        if (fornecedorRepository.existsByNome(nome)) {
            throw new AtributeAlredyExistsException("nome já cadastrado");
        }
    }

    private void validarNumeroDisponivel(String numero) {
        if (fornecedorRepository.existsByNumero(numero)) {
            throw new AtributeAlredyExistsException("numero já cadastrado");
        }
    }

    private void alreadyExists(String name,String cnpj,String email,String numero){
        validarNomeDisponivel(name);
        validarCnpjDisponivel(cnpj);
        validarEmailDisponivel(email);
        validarNumeroDisponivel(numero);
    }

    private void verificarStatusDesativo(Fornecedor fornecedor){
        if(fornecedor.getStatus() == StatusCommon.DESATIVO){
            throw new AtributeAlredyExistsException("fornecedor ja desativo!");
        }
    }

    private void verificarStatusAtivo(Fornecedor fornecedor){
        if(fornecedor.getStatus() == StatusCommon.ATIVO){
            throw new AtributeAlredyExistsException("fornecedor ja ativo!");
        }
    }

    private StatusCommon parseStatus(String status) {
        return status == null ? null : StatusCommon.valueOf(status.toUpperCase());
    }

    private String normalize(String value) {
        return value == null ? "" : value;
    }
}
