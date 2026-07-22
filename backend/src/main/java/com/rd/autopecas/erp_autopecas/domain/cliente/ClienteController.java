package com.rd.autopecas.erp_autopecas.domain.cliente;

import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteRequest;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteResponse;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteResumoResponse;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE', 'ROLE_VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE', 'ROLE_VENDEDOR')")
    @GetMapping
    public ResponseEntity<List< ClienteResumoResponse>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE', 'ROLE_VENDEDOR')")
    @PostMapping
    public ResponseEntity<ClienteResponse> create(@RequestBody @Valid ClienteRequest clienteRequest) {
        return ResponseEntity.created(URI.create("/clientes")).body(clienteService.create(clienteRequest));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@RequestBody @Valid ClienteUpdateRequest clienteRequest, @PathVariable Long id){
        return ResponseEntity.ok(clienteService.update(clienteRequest,id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_GERENTE', 'ROLE_VENDEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        clienteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
