package com.rd.autopecas.erp_autopecas.domain.forma_pagamento;

import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoRequest;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoResponse;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/forma_pagamento")
public class FormaPagamentoController {

    private final FormaPagamentoService formaPagamentoService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoResponse> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(formaPagamentoService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @GetMapping
    public ResponseEntity<List<FormaPagamentoResponse>> findAll() {
        return ResponseEntity.ok(formaPagamentoService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PostMapping
    public ResponseEntity<FormaPagamentoResponse> create(@RequestBody @Valid FormaPagamentoRequest formaPagamentoRequest) {
        return ResponseEntity.created(URI.create("/forma_pagamento")).body(formaPagamentoService.create(formaPagamentoRequest));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoResponse> update(@RequestBody @Valid FormaPagamentoUpdateRequest formaPagamentoRequest, @PathVariable Long id){
        return ResponseEntity.ok(formaPagamentoService.update(formaPagamentoRequest,id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        formaPagamentoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
