package com.rd.autopecas.erp_autopecas.domain.compra;

import com.rd.autopecas.erp_autopecas.domain.compra.dto.CompraRequest;
import com.rd.autopecas.erp_autopecas.domain.compra.dto.CompraResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("compras")
public class CompraController {

    private final CompraService compraService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @PostMapping
    public ResponseEntity<CompraResponse> gerarCompra(@RequestBody @Valid CompraRequest compraRequest){
        return ResponseEntity.created(URI.create("/compras")).body(compraService.gerarCompra(compraRequest));
    }
}
