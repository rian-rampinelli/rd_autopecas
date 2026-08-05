package com.rd.autopecas.erp_autopecas.domain.forma_pagamento;

import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoRequest;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoResponse;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.dto.FormaPagamentoUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.filter.FormaPagamentoFilter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<FormaPagamentoResponse>> findAll(FormaPagamentoFilter filter, Pageable pageable) {
        return ResponseEntity.ok(formaPagamentoService.findAll(filter,pageable));
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
    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> deactivate(@PathVariable Long id){
        formaPagamentoService.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> active(@PathVariable Long id){
        formaPagamentoService.active(id);
        return ResponseEntity.ok().build();
    }
}
