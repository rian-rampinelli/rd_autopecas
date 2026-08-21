package com.rd.autopecas.erp_autopecas.domain.endereco_funcionario;

import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.FuncionarioRepository;
import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto.EnderecoFuncionarioRequest;
import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto.EnderecoFuncionarioResponse;
import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto.EnderecoFuncionarioUpdateRequest;
import com.rd.autopecas.erp_autopecas.exceptions.AtributeAlredyExistsException;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoFuncionarioService {

    private final EnderecoFuncionarioRepository enderecoFuncionarioRepository;
    private final FuncionarioRepository funcionarioRepository;


    @Transactional
    public EnderecoFuncionarioResponse findById(Long idEndereco){
        EnderecoFuncionario enderecoFuncionario = findEntityEndereco(idEndereco);
        return(EnderecoFuncionarioResponse.fromEntity(enderecoFuncionario));
    }

    public List<EnderecoFuncionarioResponse> findAll(){
        return enderecoFuncionarioRepository.findAll().stream()
                .map(enderecoFuncionario -> EnderecoFuncionarioResponse.fromEntity(enderecoFuncionario))
                .toList();
    }

    @Transactional
    public List<EnderecoFuncionarioResponse> findAllEnderecosByFuncionario(Long idFuncionario){
        Funcionario funcionario = findEntityFuncionario(idFuncionario);
        return funcionario.getEnderecoFuncionarios().stream()
                .map(enderecoFuncionario -> EnderecoFuncionarioResponse.fromEntity(enderecoFuncionario)).toList();
    }

    @Transactional
    public EnderecoFuncionarioResponse create(EnderecoFuncionarioRequest enderecoFuncionarioRequest, Long idFuncionario) {
        Funcionario funcionario = findEntityFuncionario(idFuncionario);
        boolean existe = enderecoAlredyExistsInFuncionario(enderecoFuncionarioRequest,idFuncionario);
        if (existe) {
            throw new AtributeAlredyExistsException("Funcionario já possui esse endereço");
        }
        EnderecoFuncionario enderecoFuncionario = enderecoFuncionarioRequest.toEntity();
        funcionario.addEndereco(enderecoFuncionario);
        funcionarioRepository.save(funcionario);
        return EnderecoFuncionarioResponse.fromEntity(enderecoFuncionario);
    }

    @Transactional
    public void delete(Long idEndereco, Long idFuncionario){
        EnderecoFuncionario enderecoFuncionario = findByIdAndIdFuncionario(idEndereco,idFuncionario);
        enderecoFuncionarioRepository.delete(enderecoFuncionario);
    }

    @Transactional
    public EnderecoFuncionarioResponse update(EnderecoFuncionarioUpdateRequest updateRequest, Long idEndereco, Long idFuncionario){
        EnderecoFuncionario enderecoFuncionario = findByIdAndIdFuncionario(idEndereco,idFuncionario);
        if(updateRequest.cep() != null){
            enderecoFuncionario.setCep(updateRequest.cep());
        }
        if(updateRequest.complemento() != null){
            enderecoFuncionario.setComplemento(updateRequest.complemento());
        }
        if(updateRequest.rua() != null){
            enderecoFuncionario.setRua(updateRequest.rua());
        }
        if(updateRequest.bairro() != null){
            enderecoFuncionario.setBairro(updateRequest.bairro());
        }
        if(updateRequest.numero() != null){
            enderecoFuncionario.setNumero(updateRequest.numero());
        }
        enderecoFuncionarioRepository.save(enderecoFuncionario);
        return EnderecoFuncionarioResponse.fromEntity(enderecoFuncionario);
    }


    //metodos/funções helpers
    private EnderecoFuncionario findEntityEndereco(Long id){
        EnderecoFuncionario enderecoFuncionario = enderecoFuncionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("endereço não encontrado!"));
        return enderecoFuncionario;
    }
    public Funcionario findEntityFuncionario(Long id){
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado"));
    }

    private EnderecoFuncionario findByIdAndIdFuncionario(Long idEndereco,Long idFuncionario){
        return enderecoFuncionarioRepository.findByIdAndFuncionario_Id(idEndereco,idFuncionario)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco nao pertence a esse funcionario!"));

    }

    private boolean enderecoAlredyExistsInFuncionario(EnderecoFuncionarioRequest enderecoFuncionarioRequest,Long funcionarioId){
        return enderecoFuncionarioRepository
                .existsByFuncionarioIdAndCepAndCidadeAndBairroAndRuaAndNumeroAndComplemento(
                        funcionarioId,
                        enderecoFuncionarioRequest.cep(),
                        enderecoFuncionarioRequest.cidade(),
                        enderecoFuncionarioRequest.bairro(),
                        enderecoFuncionarioRequest.rua(),
                        enderecoFuncionarioRequest.numero(),
                        enderecoFuncionarioRequest.complemento()
                );
    }
}
