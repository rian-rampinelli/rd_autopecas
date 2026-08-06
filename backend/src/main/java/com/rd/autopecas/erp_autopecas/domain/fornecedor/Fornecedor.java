package com.rd.autopecas.erp_autopecas.domain.fornecedor;

import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.common.StatusCommon;
import com.rd.autopecas.erp_autopecas.domain.compra.Compra;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fornecedor")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private StatusCommon status;

    @OneToMany(mappedBy = "fornecedor")
    @ToString.Exclude
    private List<Compra> compras = new ArrayList();

    public void addCompra(Compra compra) {
        compras.add(compra);
    }

    public void removeCompra(Compra compra) {
        compras.remove(compra);
    }

}
