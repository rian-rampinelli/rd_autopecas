package com.rd.autopecas.erp_autopecas.domain.estoque;

import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueRequest;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


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
}
