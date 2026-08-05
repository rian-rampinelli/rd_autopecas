package com.rd.autopecas.erp_autopecas.domain.forma_pagamento;


import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.common.StatusCommon;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "forma_pagamento")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private StatusCommon status;

    @Column(name = "name", nullable = false, length = 255,unique = true)
    private String name;

}
