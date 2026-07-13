package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.CargoFuncionario;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,length = 64)
    private StatusFuncionario status;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false,length = 64)
    private CargoFuncionario cargo;

    @OneToOne
    @JoinColumn(name = "id_user",nullable = false,unique = true)
    private User user;
}
