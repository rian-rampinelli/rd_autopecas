package com.rd.autopecas.erp_autopecas.domain.estoque;


import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.endereco.Endereco;
import com.rd.autopecas.erp_autopecas.domain.item_compra.ItemCompra;
import com.rd.autopecas.erp_autopecas.domain.item_estoque.ItemEstoque;
import com.rd.autopecas.erp_autopecas.domain.unidade.Unidade;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estoque")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Estoque extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidade", nullable = false)
    @ToString.Exclude
    private Unidade unidade;

    @OneToMany(mappedBy = "estoque")
    @ToString.Exclude
    private List<ItemEstoque> ItemsEstoque = new ArrayList();


    public void addItemEstoque(ItemEstoque itemEstoque) {
        ItemsEstoque.add(itemEstoque);
        itemEstoque.setEstoque(this);
    }

    public void removeItemEstoque(ItemEstoque itemEstoque) {
        ItemsEstoque.remove(itemEstoque);
        itemEstoque.setEstoque(null);
    }



}
