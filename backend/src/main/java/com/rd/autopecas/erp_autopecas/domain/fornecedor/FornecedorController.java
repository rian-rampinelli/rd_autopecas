package com.rd.autopecas.erp_autopecas.domain.fornecedor;

import com.rd.autopecas.erp_autopecas.domain.fornecedor.dto.FornecedorRequest;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.dto.FornecedorResponse;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.dto.FornecedorUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.filter.FornecedorFilter;
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
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponse> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(fornecedorService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @GetMapping
    public ResponseEntity<Page<FornecedorResponse>> findAll(FornecedorFilter filter,Pageable pageable) {
        return ResponseEntity.ok(fornecedorService.findAll(filter,pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @PostMapping
    public ResponseEntity<FornecedorResponse> create(@RequestBody @Valid FornecedorRequest fornecedorRequest) {
        return ResponseEntity.created(URI.create("/fornecedor")).body(fornecedorService.create(fornecedorRequest));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponse> update(@RequestBody @Valid FornecedorUpdateRequest fornecedorRequest, @PathVariable Long id){
        return ResponseEntity.ok(fornecedorService.update(fornecedorRequest,id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> deactivate(@PathVariable Long id){
        fornecedorService.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> active(@PathVariable Long id){
        fornecedorService.active(id);
        return ResponseEntity.ok().build();
    }


}
