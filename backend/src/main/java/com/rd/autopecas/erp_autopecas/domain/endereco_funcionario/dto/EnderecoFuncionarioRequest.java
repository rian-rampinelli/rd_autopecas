package com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto;

import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.EnderecoFuncionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import jakarta.validation.constraints.NotBlank;

public record EnderecoFuncionarioRequest(
        @NotBlank
        String rua,
        @NotBlank
        Long numero,
        @NotBlank
        String cidade,
        @NotBlank
        String bairro,
        @NotBlank
        String nome,
        @NotBlank
        String cep
) {
    public EnderecoFuncionario toEntity(Funcionario funcionario){
        EnderecoFuncionario enderecoFuncionario = new EnderecoFuncionario();
        enderecoFuncionario.setRua(this.rua());
        enderecoFuncionario.setCep(this.cep());
        enderecoFuncionario.setNumero(this.numero());
        enderecoFuncionario.setCidade(this.cidade());
        enderecoFuncionario.setBairro(this.bairro());
        enderecoFuncionario.setNome(this.nome());
        enderecoFuncionario.setFuncionario(funcionario);
        return enderecoFuncionario;
    }
}
