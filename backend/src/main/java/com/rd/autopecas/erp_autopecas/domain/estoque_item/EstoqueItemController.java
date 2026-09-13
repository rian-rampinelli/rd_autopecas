package com.rd.autopecas.erp_autopecas.domain.estoque_item;


import com.rd.autopecas.erp_autopecas.domain.estoque_item.dto.EstoqueItemResponse;
import com.rd.autopecas.erp_autopecas.domain.estoque_item.filter.EstoqueItemFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("estoqueitens")
@AllArgsConstructor
public class EstoqueItemController {

    private final EstoqueItemService estoqueItemService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA','VENDEDOR')")
    @GetMapping("{id}")
    public ResponseEntity<EstoqueItemResponse> findByid(@PathVariable Long id){
        return ResponseEntity.ok(estoqueItemService.findByid(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR','ESTOQUISTA')")
    @GetMapping
    public ResponseEntity<Page<EstoqueItemResponse>> findAll(Pageable pageable,@ModelAttribute EstoqueItemFilter filter) {
        return ResponseEntity.ok(estoqueItemService.findAll(pageable,filter));
    }
}
