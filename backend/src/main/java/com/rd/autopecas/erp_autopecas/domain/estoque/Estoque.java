package com.rd.autopecas.erp_autopecas.domain.estoque;


import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.endereco.Endereco;
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

    @Column(name = "lugar", nullable = false, length = 255)
    private String lugar;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidade", nullable = false)
    @ToString.Exclude
    private Unidade unidade;



}
