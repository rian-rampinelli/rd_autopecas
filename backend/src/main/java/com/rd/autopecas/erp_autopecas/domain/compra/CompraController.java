package com.rd.autopecas.erp_autopecas.domain.compra;

import com.rd.autopecas.erp_autopecas.domain.compra.dto.CompraRequest;
import com.rd.autopecas.erp_autopecas.domain.compra.dto.CompraResponse;
import com.rd.autopecas.erp_autopecas.domain.item_compra.dto.ItemCompraRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @PostMapping("{id}")
    public ResponseEntity<CompraResponse> adicionarItemCompra(@PathVariable Long id, @RequestBody @Valid ItemCompraRequest itemCompraRequest){
        return ResponseEntity.created(URI.create("/compras")).body(compraService.adicionarItemNaCompra(id,itemCompraRequest));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @PostMapping("{idCompra}/finalizar/estoque/{idEstoque}")
    public ResponseEntity<CompraResponse> finalizarCompra(@PathVariable Long idEstoque,@PathVariable Long idCompra){
        return ResponseEntity.created(URI.create("/compras")).body(compraService.finalizarCompra(idEstoque,idCompra));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'ESTOQUISTA')")
    @DeleteMapping("{idCompra}/itemcompra/{idItemCompra}")
    public ResponseEntity<CompraResponse> removerItemCompra(@PathVariable Long idCompra,@PathVariable Long idItemCompra){
        return ResponseEntity.created(URI.create("/compras")).body(compraService.removerItemDaCompra(idCompra,idItemCompra));
    }
}
