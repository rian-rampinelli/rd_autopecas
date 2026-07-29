package com.rd.autopecas.erp_autopecas.domain.estoque;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.Item.ItemRepository;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.EstoqueItem;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.EstoqueItemRepository;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemRequest;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.MovimentacaoEstoque;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.MovimentacaoEstoqueRepository;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.dto.MovimentacaoEstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.enums.TypeMovimentacao;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;
import com.rd.autopecas.erp_autopecas.domain.unidade.UnidadeRepository;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final EstoqueItemRepository estoqueItemRepository;
    private final UnidadeRepository unidadeRepository;
    private final ItemRepository itemRepository;
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public EstoqueResponse findById(Long id){
        Estoque estoque = findEntityEstoque(id);
        return(EstoqueResponse.fromEntity(estoque));
    }

    public List<EstoqueItemResponse> buscarItemsDeEstoque(Long idEstoque){
        return estoqueRepository.findAllItemsByEstoque(idEstoque);
    }

    public List<MovimentacaoEstoqueResponse> buscarHistoricoMovimentacoes(Long idEstoque,Long idItem){
        if(idItem == null){
            return estoqueRepository.historicoEstoque(idEstoque);
        }
        return estoqueRepository.historicoEstoquePorItem(idEstoque,idItem);

    }

    public void deleteById(Long id){
        findEntityEstoque(id);
        estoqueRepository.deleteById(id);
    }

    @Transactional
    public EstoqueItemResponse adicionarItem(Long idEstoque, EstoqueItemRequest estoqueItemRequest){
        EstoqueItem estoqueItem = findByIdEstoqueAndItem(idEstoque,estoqueItemRequest.idItem());
        if(estoqueItem == null){
            Estoque estoque = findEntityEstoque(idEstoque);
            Item item = findEntityItem(estoqueItemRequest.idItem());
            estoqueItem = new EstoqueItem();
            estoqueItem.setQuantidade(estoqueItemRequest.quantidade());
            estoqueItem.setLocalizacao(estoqueItemRequest.localizacao());
            estoque.addEstoqueItem(estoqueItem);
            estoqueItem.setItem(item);
        }
        else{
            estoqueItem.adicionarQuantidade(estoqueItemRequest.quantidade());
        }
        estoqueItemRepository.save(estoqueItem);
        registrarTransacao(estoqueItemRequest.quantidade(),TypeMovimentacao.ENTRADA,estoqueItem);
        return EstoqueItemResponse.fromEntity(estoqueItem);
    }

    @Transactional
    public EstoqueItemResponse removerItem(Long idEstoque, EstoqueItemRequest estoqueItemRequest){
        EstoqueItem estoqueItem = findByIdEstoqueAndItem(idEstoque,estoqueItemRequest.idItem());
        if(estoqueItem == null){
            throw new ResourceNotFoundException("nao existe esse item nesse estoque!");
        }
        //n uso save pois o hibernate ja gerencia com o @Transactional,fazendo um update no final
        estoqueItem.removerQuantidade(estoqueItemRequest.quantidade());
        registrarTransacao(estoqueItemRequest.quantidade(),TypeMovimentacao.SAIDA,estoqueItem);
        return EstoqueItemResponse.fromEntity(estoqueItem);
    }


    private MovimentacaoEstoque registrarTransacao(BigDecimal qtd, TypeMovimentacao typeMovimentacao, EstoqueItem estoqueItem) {
        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setQuantidade(qtd);
        movimentacaoEstoque.setTypeMovimentacao(typeMovimentacao);
        estoqueItem.addMovimentacao(movimentacaoEstoque);
        movimentacaoEstoqueRepository.save(movimentacaoEstoque);
        return movimentacaoEstoque;
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

    public Item findEntityItem(Long id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado"));
    }

    public EstoqueItem findByIdEstoqueAndItem(Long idEstoque,Long idItem){
        return estoqueItemRepository.findByEstoque_IdAndItem_Id(idEstoque,idItem)
                .orElse(null);
    }

    public EstoqueItem findEntityEstoqueItem(Long idEstoqueItem){
        return estoqueItemRepository.findById(idEstoqueItem)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado nesse estoque!"));
    }


}
