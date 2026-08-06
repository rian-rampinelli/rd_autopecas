package com.rd.autopecas.erp_autopecas.domain.cliente;

import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteRequest;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteResponse;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteUpdateRequest;
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
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping
    public ResponseEntity<Page< ClienteResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(clienteService.findAll(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<ClienteResponse> create(@RequestBody @Valid ClienteRequest clienteRequest) {
        return ResponseEntity.created(URI.create("/clientes")).body(clienteService.create(clienteRequest));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@RequestBody @Valid ClienteUpdateRequest clienteRequest, @PathVariable Long id){
        return ResponseEntity.ok(clienteService.update(clienteRequest,id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{id}/desativar")
    public ResponseEntity<Void> deactivate(@PathVariable Long id){
        clienteService.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> active(@PathVariable Long id){
        clienteService.active(id);
        return ResponseEntity.ok().build();
    }

}
