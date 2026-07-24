package com.rd.autopecas.erp_autopecas.domain.carro;

import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroRequest;
import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroResponse;
import com.rd.autopecas.erp_autopecas.domain.carro.dto.CarroUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/carros")
@RestController
@AllArgsConstructor
public class CarroController {

    private final CarroService carroService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<CarroResponse> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(carroService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping
    public ResponseEntity<List<CarroResponse>> findAll() {
        return ResponseEntity.ok(carroService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<CarroResponse> create(@RequestBody @Valid CarroRequest carroRequest) {
        return ResponseEntity.created(URI.create("/carros")).body(carroService.create(carroRequest));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<CarroResponse> update(@RequestBody @Valid CarroUpdateRequest carroRequest, @PathVariable Long id){
        return ResponseEntity.ok(carroService.update(carroRequest,id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        carroService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
