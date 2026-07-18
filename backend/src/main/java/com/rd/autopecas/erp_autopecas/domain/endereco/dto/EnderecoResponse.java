package com.rd.autopecas.erp_autopecas.domain.endereco.dto;
import com.rd.autopecas.erp_autopecas.domain.endereco.Endereco;

public record EnderecoResponse(
        String rua,
        Long numero,
        String cidade,
        String bairro,
        String nome,
        String cep
) {
    public static EnderecoResponse fromEntity(Endereco endereco){
        return new EnderecoResponse(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getNome(),
                endereco.getCep()
        );
    }
}
