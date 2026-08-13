package com.rd.autopecas.erp_autopecas.domain.endereco_cliente;

import com.rd.autopecas.erp_autopecas.domain.endereco_cliente.dto.EnderecoClienteResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/enderecoscliente")
public class EnderecoClienteController {
    private final EnderecoClienteService enderecoClienteService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("{idEndereco}")
    public ResponseEntity<EnderecoClienteResponse> findById(@PathVariable Long idEndereco) {
        return ResponseEntity.ok(enderecoClienteService.findById(idEndereco));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @GetMapping
    public ResponseEntity<List<EnderecoClienteResponse>> findAll(){
        return ResponseEntity.ok(enderecoClienteService.findAll());
    }


}
