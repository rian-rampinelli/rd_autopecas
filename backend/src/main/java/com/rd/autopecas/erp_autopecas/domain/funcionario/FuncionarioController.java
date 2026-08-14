package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.funcionario.dto.FuncionarioResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PreAuthorize("hasAnyRole('GERENTE','RH','ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<FuncionarioResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(funcionarioService.findById(id));
    }

    @PreAuthorize("hasAnyRole('GERENTE','RH','ADMIN')")
    @GetMapping
    public ResponseEntity<Page<FuncionarioResponse>> findById(Pageable pageable){
        return ResponseEntity.ok(funcionarioService.findAll(pageable));
    }

    @PreAuthorize("hasAnyRole('GERENTE','RH','ADMIN')")
    @PatchMapping("{id}/status")
    public ResponseEntity<FuncionarioResponse> findById(@PathVariable Long id,@RequestParam String status){
        return ResponseEntity.ok(funcionarioService.changeStatus(id,status));
    }

}
