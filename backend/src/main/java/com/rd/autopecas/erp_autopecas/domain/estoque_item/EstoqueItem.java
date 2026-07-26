package com.rd.autopecas.erp_autopecas.domain.estoque_item;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.estoque.Estoque;
import com.rd.autopecas.erp_autopecas.exceptions.ValidationException;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;

@Entity
@Table(name = "estoque_item")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueItem extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade",nullable = false,precision = 10,scale = 2)
    private BigDecimal quantidade;

    @Column(name = "localizacao", nullable = false, length = 255)
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "id_estoque", nullable = false)
    private Estoque estoque;

    public void adicionarQuantidade(BigDecimal quantidadeAdd) {
        if (quantidadeAdd.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("valor menor ou igual a zero!");
        } else {
            setQuantidade(quantidade.add(quantidadeAdd));
        }
        ;
    }
}
