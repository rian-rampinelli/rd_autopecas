package com.rd.autopecas.erp_autopecas.domain.estoque;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.Item.ItemRepository;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueItemProjection;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.EstoqueItem;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.EstoqueItemRepository;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.filter.EstoqueItemFilter;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.MovimentacaoEstoqueRepository;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.dto.MovimentacaoEstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.enums.TypeMovimentacao;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.filter.MovimentacaoEstoqueFilter;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstoqueBuscaService {

    private final EstoqueRepository estoqueRepository;

    @Transactional
    public EstoqueResponse findById(Long id){
        Estoque estoque = findEntityEstoque(id);
        return(EstoqueResponse.fromEntity(estoque));
    }

    public List<EstoqueItemResponse> buscarItemsDeEstoque(Long idEstoque, EstoqueItemFilter filter){
        return estoqueRepository.findAllItemsByEstoque(idEstoque,filter.item(),filter.nomeItem(),filter.localizacao(),filter.qtdMinima(),filter.qtdMaxima());
    }

    public List<MovimentacaoEstoqueResponse> buscarHistoricoMovimentacoesDeEstoque(Long idEstoque, MovimentacaoEstoqueFilter filter){
        String tipo = filter.tipo();
        if (tipo != null) {
            tipo = tipo.toUpperCase();
            validaValorEnum(tipo);
        }
        return estoqueRepository.buscarHistoricoEstoque(idEstoque,filter.item(),filter.nomeItem(),tipo,filter.qtdMinima(),filter.qtdMaxima());
    }

    //helpers
    public Estoque findEntityEstoque(Long id){
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado"));
    }

    private void validaValorEnum(String tipo){
        try {
            TypeMovimentacao.valueOf(tipo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de movimentação/enum inválido");
        }
    }
}
