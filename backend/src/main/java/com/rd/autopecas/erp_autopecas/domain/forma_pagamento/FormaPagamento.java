package com.rd.autopecas.erp_autopecas.domain.forma_pagamento;


import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
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

    @Column(name = "name", nullable = false, length = 255)
    private String name;

}
