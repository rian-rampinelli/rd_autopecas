package com.rd.autopecas.erp_autopecas.domain.cliente;

import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteRequest;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteResponse;
import com.rd.autopecas.erp_autopecas.domain.cliente.dto.ClienteUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.cliente.filter.ClienteFilter;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.EnderecoClienteService;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteRequest;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteResponse;
import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteUpdateRequest;
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
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final EnderecoClienteService enderecoClienteService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> findAll(ClienteFilter filter,Pageable pageable) {
        return ResponseEntity.ok(clienteService.findAll(filter,pageable));
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

    //endereco de cliente

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("{idCliente}/enderecos")
    public ResponseEntity<List<EnderecoClienteResponse>> findAllEnderecosByCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(enderecoClienteService.findAllEnderecosByCliente(idCliente));
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
