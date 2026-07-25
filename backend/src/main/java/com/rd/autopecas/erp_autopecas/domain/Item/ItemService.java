package com.rd.autopecas.erp_autopecas.domain.Item;


import com.rd.autopecas.erp_autopecas.domain.Item.dto.*;
import com.rd.autopecas.erp_autopecas.domain.carro.Carro;
import com.rd.autopecas.erp_autopecas.domain.carro.CarroRepository;
import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroResponse;
import com.rd.autopecas.erp_autopecas.exceptions.ResourceNotFoundException;
import com.rd.autopecas.erp_autopecas.exceptions.ValidationException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;


@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CarroRepository carroRepository;

    @Transactional
    public ItemResponse findById(Long id){
        Item item = findEntityItem(id);
        return(ItemResponse.fromEntity(item));
    }

    public List<ItemResumeResponse> findAll(){
        return itemRepository.findAll().stream()
                .map(item -> ItemResumeResponse.fromEntity(item))
                .toList();
    }

    @Transactional
    public ItemResponse create(ItemRequest itemRequest) {
        Item item = itemRequest.toEntity();

        if(itemRequest.idsCarros() != null){
            List<Carro> carros = new ArrayList<>(carroRepository.findAllById(itemRequest.idsCarros()));
            if (carros.size() != itemRequest.idsCarros().size()) {
                throw new ValidationException("Um ou mais carros não existem");
            }
            item.setCarros(carros);
        }
        itemRepository.save(item);
        return ItemResponse.fromEntity(item);
    }

    public void deleteById(Long id){
        findEntityItem(id);
        itemRepository.deleteById(id);
    }

    @Transactional
    public ItemResponse update(ItemUpdateRequest updateRequest, Long id){
        Item item = findEntityItem(id);
        if (updateRequest.codigo() != null) {
            item.setCodigo(updateRequest.codigo());
        }

        if (updateRequest.name() != null) {
            item.setNome(updateRequest.name());
        }

        if (updateRequest.descricao() != null) {
            item.setDescricao(updateRequest.descricao());
        }

        if (updateRequest.marca() != null) {
            item.setMarca(updateRequest.marca());
        }

        if (updateRequest.typeItem() != null) {
            item.setTypeItem(updateRequest.typeItem());
        }

        if (updateRequest.standartPrice() != null) {
            item.setStandartPrice(updateRequest.standartPrice());
        }
        itemRepository.save(item);
        return ItemResponse.fromEntity(item);
    }

    @Transactional
    public ItemResponse addCarrosAItem(Long itemId, ListCarrosRequest listCarrosRequest){
        Item item = findEntityItem(itemId);
        List<Carro> carros = new ArrayList<>(carroRepository.findAllById(listCarrosRequest.idsCarros()));
        if (carros.size() != listCarrosRequest.idsCarros().size()) {
            throw new ValidationException("Um ou mais carros não existem");
        }

        for(Carro carro: carros){
            if(!item.getCarros().contains(carro)){
                item.addCarro(carro);
            }
        }
        itemRepository.save(item);
        return ItemResponse.fromEntity(item);
    }

    @Transactional
    public ItemResponse removeCarroDoItem(Long itemId,Long carroId){
        Item item = findEntityItem(itemId);
        Carro carro = findEntityCarro(carroId);
        carro.removeItem(item);
        itemRepository.save(item);
        return ItemResponse.fromEntity(item);
    }

    @Transactional
    public List<CarroResponse> findAllCarsByItem(Long itemId){
        List<Carro> carros = itemRepository.findAllCarsByItemId(itemId);
        return carros.stream()
                .map(carro -> CarroResponse.fromEntity(carro))
                .toList();
    }

    //helpers
    private Item findEntityItem(Long id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado"));
    }
    private Carro findEntityCarro(Long id){
        return carroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carro não encontrado"));
    }

    
}
