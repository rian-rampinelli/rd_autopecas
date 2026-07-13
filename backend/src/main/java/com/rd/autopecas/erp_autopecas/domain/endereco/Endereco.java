package com.rd.autopecas.erp_autopecas.domain.endereco;

import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Endereco extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "cidade", nullable = false, length = 255)
    private String cidade;

    @Column(name = "cep", nullable = false, length = 64)
    private String cep;

    @Column(name = "rua", nullable = false, length = 255)
    private String rua;

    @Column(name = "bairro", nullable = false, length = 255)
    private String bairro;

    @Column(name = "numero", nullable = false)
    private Long numero;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
