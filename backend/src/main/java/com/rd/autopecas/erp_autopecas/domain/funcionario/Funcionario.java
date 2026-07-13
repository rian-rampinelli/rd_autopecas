package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.compra.Compra;
import com.rd.autopecas.erp_autopecas.domain.endereco.Endereco;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.CargoFuncionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import com.rd.autopecas.erp_autopecas.domain.venda.Venda;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,length = 64)
    private StatusFuncionario status;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false,length = 64)
    private CargoFuncionario cargo;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @OneToOne
    @JoinColumn(name = "id_user",nullable = false,unique = true)
    private User user;

    @OneToMany(mappedBy = "funcionario")
    @ToString.Exclude
    private List<Venda> vendas = new ArrayList();

    @OneToMany(mappedBy = "funcionario")
    @ToString.Exclude
    private List<Compra> compras = new ArrayList();


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
