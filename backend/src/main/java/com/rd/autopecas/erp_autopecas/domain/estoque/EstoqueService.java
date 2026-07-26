package com.rd.autopecas.erp_autopecas.domain.estoque;

import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;
import com.rd.autopecas.erp_autopecas.domain.unidade.UnidadeRepository;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final UnidadeRepository unidadeRepository;

    public EstoqueResponse findById(Long id){
        Estoque estoque = findEntityEstoque(id);
        return(EstoqueResponse.fromEntity(estoque));
    }

    public void deleteById(Long id){
        findEntityEstoque(id);
        estoqueRepository.deleteById(id);
    }

    //helpers
    public Estoque findEntityEstoque(Long id){
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado"));
    }

    public Unidade findEntityUnidade(Long id){
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unidade não encontrado"));
    }


}
