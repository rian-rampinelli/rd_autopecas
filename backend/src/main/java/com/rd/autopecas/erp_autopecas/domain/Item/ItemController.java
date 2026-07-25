package com.rd.autopecas.erp_autopecas.domain.Item;


import com.rd.autopecas.erp_autopecas.domain.Item.dto.*;
import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR','ESTOQUISTA')")
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR','ESTOQUISTA')")
    @GetMapping
    public ResponseEntity<List<ItemResponse>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<ItemResponse> create(@RequestBody @Valid ItemRequest itemRequest) {
        return ResponseEntity.created(URI.create("/items")).body(itemService.create(itemRequest));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> update(@RequestBody @Valid ItemUpdateRequest itemRequest, @PathVariable Long id){
        return ResponseEntity.ok(itemService.update(itemRequest,id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @DeleteMapping("{idItem}/carros/{idCarro}")
    public ResponseEntity<ItemResponse> removeCarroDoItem(@PathVariable Long idItem, @PathVariable Long idCarro) {
        itemService.removeCarroDoItem(idItem, idCarro);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @PostMapping("{idItem}/carros")
    public ResponseEntity<ItemResponse> addCarroAoItem(@PathVariable Long idItem,@RequestBody ListCarrosRequest listCarrosRequest ) {
        return ResponseEntity.created(URI.create("/items")).body(itemService.addCarrosAItem(idItem,listCarrosRequest));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR','ESTOQUISTA')")
    @GetMapping("{idItem}/carros")
    public ResponseEntity<List<CarroResponse>> findAllCarroByItem(@PathVariable Long idItem) {
        return ResponseEntity.ok(itemService.findAllCarsByItem(idItem));
    }

}
