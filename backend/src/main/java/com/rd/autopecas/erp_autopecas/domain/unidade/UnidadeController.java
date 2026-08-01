package com.rd.autopecas.erp_autopecas.domain.unidade;

import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueRequest;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.estoque.dto.EstoqueResumeResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeEstoqueResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeRequest;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeResponse;
import com.rd.autopecas.erp_autopecas.domain.unidade.dto.UnidadeUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.unidade.filter.UnidadeFilter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/unidades")
public class UnidadeController {
    
    private final UnidadeService unidadeService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR','ESTOQUISTA')")
    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponse> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(unidadeService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR','ESTOQUISTA')")
    @GetMapping
    public ResponseEntity<Page<UnidadeEstoqueResponse>> findAll(@ModelAttribute UnidadeFilter filter, Pageable pageable) {
        return ResponseEntity.ok(unidadeService.buscarUnidades(filter,pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR','ESTOQUISTA')")
    @GetMapping("/{id}/estoques")
    public ResponseEntity<Page<EstoqueResumeResponse>> findAllEstoquesByUnidade(@PathVariable  Long id, Pageable pageable) {
        return ResponseEntity.ok(unidadeService.findAllEstoquesByUnidadeId(id,pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PostMapping
    public ResponseEntity<UnidadeResponse> create(@RequestBody @Valid UnidadeRequest unidadeRequest) {
        return ResponseEntity.created(URI.create("/unidades")).body(unidadeService.create(unidadeRequest));
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PostMapping("/{idUnidade}/estoques")
    public ResponseEntity<EstoqueResponse> createEstoque(@RequestBody @Valid EstoqueRequest estoqueRequest,@PathVariable Long idUnidade) {
        return ResponseEntity.created(URI.create("/unidades")).body(unidadeService.createEstoque(estoqueRequest,idUnidade));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<UnidadeResponse> update(@RequestBody @Valid UnidadeUpdateRequest unidadeRequest, @PathVariable Long id){
        return ResponseEntity.ok(unidadeService.update(unidadeRequest,id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        unidadeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
