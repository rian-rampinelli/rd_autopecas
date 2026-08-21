package com.rd.autopecas.erp_autopecas.domain.endereco_funcionario;


import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto.EnderecoFuncionarioResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("enderecofuncionario")
public class EnderecoFuncionarioController {

    private final EnderecoFuncionarioService enderecoFuncionarioService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("{idEndereco}")
    public ResponseEntity<EnderecoFuncionarioResponse> findById(@PathVariable Long idEndereco) {
        return ResponseEntity.ok(enderecoFuncionarioService.findById(idEndereco));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @GetMapping
    public ResponseEntity<List<EnderecoFuncionarioResponse>> findAll(){
        return ResponseEntity.ok(enderecoFuncionarioService.findAll());
    }
}
