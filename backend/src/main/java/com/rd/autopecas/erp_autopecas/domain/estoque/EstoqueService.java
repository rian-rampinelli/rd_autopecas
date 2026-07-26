package com.rd.autopecas.erp_autopecas.domain.estoque;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.Item.ItemRepository;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.EstoqueItem;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.EstoqueItemRepository;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemRequest;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;
import com.rd.autopecas.erp_autopecas.domain.unidade.UnidadeRepository;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final EstoqueItemRepository estoqueItemRepository;
    private final UnidadeRepository unidadeRepository;
    private final ItemRepository itemRepository;

    public EstoqueResponse findById(Long id){
        Estoque estoque = findEntityEstoque(id);
        return(EstoqueResponse.fromEntity(estoque));
    }

    public void deleteById(Long id){
        findEntityEstoque(id);
        estoqueRepository.deleteById(id);
    }

    @Transactional
    public EstoqueItemResponse adicionarItem(Long idEstoque, EstoqueItemRequest estoqueItemRequest){
        EstoqueItem estoqueItem = findByIdEstoqueAndItem(idEstoque,estoqueItemRequest.idItem());
        Estoque estoque = findEntityEstoque(idEstoque);
        Item item = findEntityItem(estoqueItemRequest.idItem());
        if(estoqueItem == null){
            estoqueItem = new EstoqueItem();
            estoqueItem.setQuantidade(estoqueItemRequest.quantidade());
            estoqueItem.setLocalizacao(estoqueItemRequest.localizacao());
            estoque.addEstoqueItem(estoqueItem);
            estoqueItem.setItem(item);
        }
        else{
            estoqueItem.adicionarQuantidade(estoqueItemRequest.quantidade());
        }
        estoqueRepository.save(estoque);
        estoqueItemRepository.save(estoqueItem);
        return EstoqueItemResponse.fromEntity(estoqueItem);
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

}
