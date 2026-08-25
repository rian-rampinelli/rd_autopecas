package com.rd.autopecas.erp_autopecas.domain.funcionario;


import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.EnderecoFuncionarioService;
import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto.EnderecoFuncionarioRequest;
import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto.EnderecoFuncionarioResponse;
import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto.EnderecoFuncionarioUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.funcionario.dto.FuncionarioResponse;
import com.rd.autopecas.erp_autopecas.domain.funcionario.dto.FuncionarioUpdateRequest;
import com.rd.autopecas.erp_autopecas.domain.funcionario.filter.FuncionarioFilter;
import com.rd.autopecas.erp_autopecas.domain.role.dto.RoleResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final FuncionarioRoleService funcionarioRoleService;
    private final EnderecoFuncionarioService enderecoFuncionarioService;

    @PreAuthorize("hasAnyRole('GERENTE','RH','ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<FuncionarioResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(funcionarioService.findById(id));
    }

    @PreAuthorize("hasAnyRole('GERENTE','RH','ADMIN')")
    @GetMapping
    public ResponseEntity<Page<FuncionarioResponse>> findAll(FuncionarioFilter filter,Pageable pageable){
        return ResponseEntity.ok(funcionarioService.findAllWithFilter(filter,pageable));
    }

    @PreAuthorize("hasAnyRole('GERENTE','RH','ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<FuncionarioResponse> Update(@PathVariable Long id, @RequestBody @Valid FuncionarioUpdateRequest updateRequest){
        return ResponseEntity.ok(funcionarioService.update(id,updateRequest));
    }

    @PreAuthorize("hasAnyRole('GERENTE','RH','ADMIN')")
    @PatchMapping("{id}/status")
    public ResponseEntity<FuncionarioResponse> changeStatus(@PathVariable Long id,@RequestParam String status){
        return ResponseEntity.ok(funcionarioService.changeStatus(id,status));
    }

    //endereco de funcionario
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("{idFuncionario}/enderecos")
    public ResponseEntity<List<EnderecoFuncionarioResponse>> findAllEnderecosByFuncionario(@PathVariable Long idFuncionario) {
        return ResponseEntity.ok(enderecoFuncionarioService.findAllEnderecosByFuncionario(idFuncionario));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PostMapping("{idFuncionario}/enderecos")
    public ResponseEntity<EnderecoFuncionarioResponse> create(@RequestBody @Valid EnderecoFuncionarioRequest enderecoFuncionarioRequest, @PathVariable Long idFuncionario) {
        return ResponseEntity.created(URI.create("/clientes/" + idFuncionario + "/enderecos/" ))
                .body(enderecoFuncionarioService.create(enderecoFuncionarioRequest,idFuncionario));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @PutMapping("/{idFuncionario}/enderecos/{idEndereco}")
    public ResponseEntity<EnderecoFuncionarioResponse> update(@RequestBody @Valid EnderecoFuncionarioUpdateRequest enderecoFuncionarioUpdateRequest, @PathVariable Long idEndereco, @PathVariable Long idFuncionario){
        return ResponseEntity.ok(enderecoFuncionarioService.update(enderecoFuncionarioUpdateRequest,idEndereco,idFuncionario));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @DeleteMapping("/{idFuncionario}/enderecos/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable Long idEndereco,  @PathVariable Long idFuncionario){
        enderecoFuncionarioService.delete(idEndereco,idFuncionario);
        return ResponseEntity.noContent().build();
    }

    //roles de funcionario
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'RH')")
    @GetMapping("{idFuncionario}/roles")
    public ResponseEntity<Set<RoleResponse>> findAllRoleByFuncionario(@PathVariable Long idFuncionario) {
        return ResponseEntity.ok(funcionarioRoleService.buscarRoles(idFuncionario));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{idFuncionario}/adicionar_roles/{idRole}")
    public ResponseEntity<FuncionarioResponse> addRole( @PathVariable Long idRole, @PathVariable Long idFuncionario){
        return ResponseEntity.ok(funcionarioRoleService.addRole(idFuncionario,idRole));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/{idFuncionario}/remover_roles/{idRole}")
    public ResponseEntity<FuncionarioResponse> removerRole( @PathVariable Long idRole, @PathVariable Long idFuncionario){
        return ResponseEntity.ok(funcionarioRoleService.removerRole(idFuncionario,idRole));
    }


}
