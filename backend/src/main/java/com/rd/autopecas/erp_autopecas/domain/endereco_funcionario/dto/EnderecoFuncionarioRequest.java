package com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto;

import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.EnderecoFuncionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoFuncionarioRequest(
        @NotBlank
        String rua,
        @NotNull(message = "Número é obrigatório")
        Long numero,
        @NotBlank
        String cidade,
        @NotBlank
        String bairro,
        @NotBlank
        String complemento,
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
        enderecoFuncionario.setComplemento(this.complemento());
        enderecoFuncionario.setFuncionario(funcionario);
        return enderecoFuncionario;
    }
}
