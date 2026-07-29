package com.rd.autopecas.erp_autopecas.domain.unidade;

import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.domain.estoque.EstoqueRepository;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueRequest;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeRequest;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.unidade.enums.StatusUnidade;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UnidadeService {
    
    private final UnidadeRepository unidadeRepository;
    private final EstoqueRepository estoqueRepository;


    public UnidadeResponse findById(Long id){
        Unidade unidade = findEntityUnidade(id);
        return(UnidadeResponse.fromEntity(unidade));
    }

    public List<UnidadeResponse> buscarUnidades(String status){
        if(status == null){
            return unidadeRepository.findUnidadesWithEstoques().stream()
                    .map(unidade -> UnidadeResponse.fromEntity(unidade))
                    .toList();
        }
        return unidadeRepository.findUnidadesByStatus(StatusUnidade.valueOf(status.toUpperCase())).stream()
                .map(unidade -> UnidadeResponse.fromEntity(unidade))
                .toList();
    }

    public List<EstoqueResponse> findAllEstoquesByUnidadeId(Long id){
        List<EstoqueResponse> estoques = unidadeRepository.findAllEstoquesByUnidade(id).stream()
                .map(estoque -> EstoqueResponse.fromEntity(estoque))
                .toList();
        return estoques;
    }


    public UnidadeResponse create(UnidadeRequest unidadeRequest) {
        Unidade unidade = unidadeRequest.toEntity();

        unidadeRepository.save(unidade);
        return UnidadeResponse.fromEntity(unidade);
    }

    public void deleteById(Long id){
        findEntityUnidade(id);
        unidadeRepository.deleteById(id);
    }

    @Transactional
    public UnidadeResponse update(UnidadeUpdateRequest updateRequest, Long id){
        Unidade unidade = findEntityUnidade(id);

        if(updateRequest.endereco() != null){
            unidade.setEndereco(updateRequest.endereco());
        }
        if(updateRequest.status() != null){
            unidade.setStatus(StatusUnidade.valueOf(updateRequest.status().toUpperCase()));
        }
        unidadeRepository.save(unidade);
        return UnidadeResponse.fromEntity(unidade);
    }




    @Transactional
    public EstoqueResponse createEstoque(EstoqueRequest estoqueRequest,Long idUnidade) {
        Unidade unidade = findEntityUnidade(idUnidade);
        Estoque estoque = estoqueRequest.toEntity();
        unidade.addEstoque(estoque);
        unidadeRepository.save(unidade);
        estoqueRepository.save(estoque);
        return EstoqueResponse.fromEntity(estoque);
    }



    //helpers
    public Unidade findEntityUnidade(Long id){
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidade não encontrado"));
    }

    public Estoque findEntityEstoque(Long id){
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado"));
    }



   
}
