package com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.dto;
import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.EnderecoFuncionario;

public record EnderecoFuncionarioResponse(
        String rua,
        Long numero,
        String cidade,
        String bairro,
        String nome,
        String cep
) {
    public static EnderecoFuncionarioResponse fromEntity(EnderecoFuncionario enderecoFuncionario){
        return new EnderecoFuncionarioResponse(
                enderecoFuncionario.getRua(),
                enderecoFuncionario.getNumero(),
                enderecoFuncionario.getCidade(),
                enderecoFuncionario.getBairro(),
                enderecoFuncionario.getNome(),
                enderecoFuncionario.getCep()
        );
    }
}
