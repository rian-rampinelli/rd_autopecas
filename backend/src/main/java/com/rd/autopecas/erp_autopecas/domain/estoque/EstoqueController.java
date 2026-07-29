package com.rd.autopecas.erp_autopecas.domain.estoque;


import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemRequest;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import com.rd.autopecas.erp_autopecas.domain.movimentacao_estoque.dto.MovimentacaoEstoqueResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/estoques")
@AllArgsConstructor
public class EstoqueController {
    
    private final EstoqueService estoqueService;
    
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA','VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(estoqueService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        estoqueService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @PostMapping("/{idEstoque}/items")
    public ResponseEntity<EstoqueItemResponse> adicionarItem(@PathVariable  Long idEstoque,@RequestBody @Valid EstoqueItemRequest estoqueItemRequest) {
        return ResponseEntity.ok(estoqueService.adicionarItem(idEstoque,estoqueItemRequest));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA','VENDEDOR')")
    @PutMapping ("/{idEstoque}/items")
    public ResponseEntity<EstoqueItemResponse> removerItem(@PathVariable  Long idEstoque,@RequestBody @Valid EstoqueItemRequest estoqueItemRequest) {
        return ResponseEntity.ok(estoqueService.removerItem(idEstoque,estoqueItemRequest));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA','VENDEDOR')")
    @GetMapping("/{idEstoque}/items")
    public ResponseEntity<List<EstoqueItemResponse>> buscarItems(@PathVariable  Long idEstoque) {
        return ResponseEntity.ok(estoqueService.buscarItemsDeEstoque(idEstoque));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA','VENDEDOR')")
    @GetMapping("/{idEstoque}/movimentacoes")
    public ResponseEntity<List<MovimentacaoEstoqueResponse>> buscarHistoricoMovimetacoes(@RequestParam(value = "item",required = false)Long item, @PathVariable  Long idEstoque) {
        return ResponseEntity.ok(estoqueService.buscarHistoricoMovimentacoes(idEstoque,item));

    }




}
