package com.rd.autopecas.erp_autopecas.domain.compra;

import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;
import com.rd.autopecas.erp_autopecas.domain.fornecedor.Fornecedor;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import com.rd.autopecas.erp_autopecas.domain.common.StatusTransacao;
import com.rd.autopecas.erp_autopecas.domain.item_compra.ItemCompra;
import com.rd.autopecas.erp_autopecas.domain.item_venda.ItemVenda;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compra")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Compra extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 128)
    private StatusTransacao status;

    @Column(name = "total_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<ItemCompra> ItemsCompra = new ArrayList();


    public void addItemCompra(ItemCompra itemCompra) {
        ItemsCompra.add(itemCompra);
        itemCompra.setCompra(this);
    }

    public void removeItemCompra(ItemCompra itemCompra) {
        ItemsCompra.remove(itemCompra);
        itemCompra.setCompra(null);
    }
}