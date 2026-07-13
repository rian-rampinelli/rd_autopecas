package com.rd.autopecas.erp_autopecas.domain.user;


import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 128)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Endereco> enderecos = new ArrayList<>();

    public void addEndereco(Endereco endereco) {
        enderecos.add(endereco);
        endereco.setUser(this);
    }

    public void removeEndereco(Endereco endereco) {
        enderecos.remove(endereco);
        endereco.setUser(null);
    }

}
