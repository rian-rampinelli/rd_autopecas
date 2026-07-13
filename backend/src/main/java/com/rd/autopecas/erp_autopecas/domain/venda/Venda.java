package com.rd.autopecas.erp_autopecas.domain.venda;

import com.rd.autopecas.erp_autopecas.domain.cliente.Cliente;
import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.forma_pagamento.FormaPagamento;
import com.rd.autopecas.erp_autopecas.domain.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "venda")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Venda extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false,length = 128)
    private StatusVenda status;

    @Column(name = "total_value",nullable = false,precision = 10,scale = 2)
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_funcionario",nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento",nullable = false)
    private FormaPagamento formaPagamento;
}
