package com.rd.autopecas.erp_autopecas.domain.carro;

import com.rd.autopecas.erp_autopecas.domain.Item.Item;
import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carro")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Carro extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "motor", nullable = false, length = 255)
    private String motor;

    @Column(name = "marca", nullable = false, length = 255)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 255)
    private String modelo;

    @Column(name = "versao", nullable = false, length = 255)
    private String versao;

    @Column(name = "ano_fabricacao",nullable = false)
    private LocalDate anoFabricacao;

    @ManyToMany(mappedBy = "carros")
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
        item.addCarro(this);
    }

    public void removeItem(Item item){
        items.remove(item);
        item.removeCarro(this);
    }

}
