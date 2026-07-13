package com.rd.autopecas.erp_autopecas.domain.Item;

import com.rd.autopecas.erp_autopecas.domain.carro.Carro;
import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true)
    private Long codigo;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 528)
    private String descricao;

    @Column(name = "marca", nullable = false, length = 255)
    private String marca;

    @Column(name = "type_item", nullable = false, length = 128)
    private String typeItem;

    @Column(name = "standard_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal standartPrice;

    @Column(name = "peso", nullable = false, precision = 10, scale = 2)
    private BigDecimal peso;

    @ManyToMany
    @JoinTable(
            name = "carro_items",
            joinColumns = @JoinColumn(name = "id_item"),
            inverseJoinColumns = @JoinColumn(name = "id_carro")
    )
    @ToString.Exclude
    private List<Carro> carros = new ArrayList<>();

    public void addCarro(Carro carro){
        carros.add(carro);
    }

    public void removeCarro(Carro carro){
        carros.remove(carro);
    }


}
