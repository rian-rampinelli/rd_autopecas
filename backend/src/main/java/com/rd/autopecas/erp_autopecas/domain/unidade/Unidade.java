package com.rd.autopecas.erp_autopecas.domain.unidade;


import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unidade")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Unidade extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "endereco", nullable = false, length = 255)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusUnidade status;

    @OneToMany(mappedBy = "unidade")
    private List<Estoque> estoques = new ArrayList<>();


    public void addEstoque(Estoque estoque) {
        estoques.add(estoque);
        estoque.setUnidade(this);
    }

    public void removeEstoque(Estoque estoque) {
        estoques.remove(estoque);
        estoque.setUnidade(null);
    }


}
