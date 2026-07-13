package com.rd.autopecas.erp_autopecas.domain.fornecedor;

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
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true, length = 255)
    private String cnpj;

    @OneToMany(mappedBy = "fornecedor")
    @ToString.Exclude
    private List<Compra> compras = new ArrayList();


    public void addCompra(Compra compra) {
        compras.add(compra);
        compra.setFornecedor(this);
    }

    public void removeCompra(Compra compra) {
        compras.remove(compra);
        compra.setFornecedor(null);
    }


}
