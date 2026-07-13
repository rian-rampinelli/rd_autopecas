package com.rd.autopecas.erp_autopecas.domain.cliente;

import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import com.rd.autopecas.erp_autopecas.domain.venda.Venda;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente  extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_user",nullable = false,unique = true)
    private User user;

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Venda> vendas = new ArrayList<>();


    public void addVenda(Venda venda) {
        vendas.add(venda);
        venda.setCliente(this);
    }

    public void removeVenda(Venda venda) {
        vendas.remove(venda);
        venda.setCliente(null);
    }
}
