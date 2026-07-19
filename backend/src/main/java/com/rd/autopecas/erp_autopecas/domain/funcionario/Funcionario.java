package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.compra.Compra;
import com.rd.autopecas.erp_autopecas.domain.endereco_funcionario.EnderecoFuncionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.domain.role.Role;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import com.rd.autopecas.erp_autopecas.domain.venda.Venda;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,length = 64)
    private StatusFuncionario status;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @Column(name = "cargo", nullable = false, length = 255)
    private String cargo;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user",nullable = false,unique = true)
    private User user;

    @OneToMany(mappedBy = "funcionario")
    @ToString.Exclude
    private List<Venda> vendas = new ArrayList();

    @OneToMany(mappedBy = "funcionario")
    @ToString.Exclude
    private List<Compra> compras = new ArrayList();

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<EnderecoFuncionario> enderecoFuncionarios = new ArrayList<>();

    public void addEndereco(EnderecoFuncionario enderecoFuncionario) {
        enderecoFuncionarios.add(enderecoFuncionario);
        enderecoFuncionario.setFuncionario(this);
    }

    public void removeEndereco(EnderecoFuncionario enderecoFuncionario) {
        enderecoFuncionarios.remove(enderecoFuncionario);
        enderecoFuncionario.setFuncionario(null);
    }


    public void addVenda(Venda venda) {
        vendas.add(venda);
        venda.setFuncionario(this);
    }

    public void removeVenda(Venda venda) {
        vendas.remove(venda);
        venda.setFuncionario(null);
    }

    public void addCompra(Compra compra) {
        compras.add(compra);
        compra.setFuncionario(this);
    }

    public void removeCompra(Compra compra) {
        compras.remove(compra);
        compra.setFuncionario(null);
    }


}
