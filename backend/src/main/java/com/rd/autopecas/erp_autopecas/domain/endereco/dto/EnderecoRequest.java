package com.rd.autopecas.erp_autopecas.domain.endereco.dto;

import com.rd.autopecas.erp_autopecas.domain.endereco.Endereco;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import jakarta.validation.constraints.NotBlank;

public record EnderecoRequest(
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
    public Endereco toEntity(User user){
        Endereco endereco = new Endereco();
        endereco.setRua(this.rua());
        endereco.setCep(this.cep());
        endereco.setNumero(this.numero());
        endereco.setCidade(this.cidade());
        endereco.setBairro(this.bairro());
        endereco.setNome(this.nome());
        endereco.setUser(user);
        return endereco;
    }
}
