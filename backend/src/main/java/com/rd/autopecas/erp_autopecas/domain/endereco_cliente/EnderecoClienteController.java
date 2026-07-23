package com.rd.autopecas.erp_autopecas.domain.endereco_cliente;

import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteRequest;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteResponse;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteUpdateRequest;
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
public class EnderecoClienteController {
    private final EnderecoClienteService enderecoClienteService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/{idCliente}/enderecos/{idEndereco}")
    public ResponseEntity<EnderecoClienteResponse> findById(@PathVariable Long idEndereco,  @PathVariable Long idCliente) {
        return ResponseEntity.ok(enderecoClienteService.findById(idEndereco,idCliente));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/{idCliente}/enderecos")
    public ResponseEntity<List<EnderecoClienteResponse>> findAll(@PathVariable Long idCliente) {
        return ResponseEntity.ok(enderecoClienteService.findAll(idCliente));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PostMapping("{idCliente}/enderecos")
    public ResponseEntity<EnderecoClienteResponse> create(@RequestBody @Valid EnderecoClienteRequest enderecoClienteRequest, @PathVariable Long idCliente) {
        return ResponseEntity.created(URI.create("/clientes/" + idCliente + "/enderecos/" ))
                .body(enderecoClienteService.create(enderecoClienteRequest,idCliente));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PutMapping("/{idCliente}/enderecos/{idEndereco}")
    public ResponseEntity<EnderecoClienteResponse> update(@RequestBody @Valid EnderecoClienteUpdateRequest enderecoClienteUpdateRequest, @PathVariable Long idEndereco, @PathVariable Long idCliente){
        return ResponseEntity.ok(enderecoClienteService.update(enderecoClienteUpdateRequest,idEndereco,idCliente));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @DeleteMapping("/{idCliente}/enderecos/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable Long idEndereco,  @PathVariable Long idCliente){
        enderecoClienteService.delete(idEndereco,idCliente);
        return ResponseEntity.noContent().build();
    }
}
