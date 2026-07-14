package com.rd.autopecas.erp_autopecas.domain.funcionario;

import com.rd.autopecas.erp_autopecas.domain.common.Auditable;
import com.rd.autopecas.erp_autopecas.domain.compra.Compra;
import com.rd.autopecas.erp_autopecas.domain.funcionario.enums.StatusFuncionario;
import com.rd.autopecas.erp_autopecas.domain.role.Role;
import com.rd.autopecas.erp_autopecas.domain.user.User;
import com.rd.autopecas.erp_autopecas.domain.venda.Venda;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends Auditable implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,length = 64)
    private StatusFuncionario status;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "funcionario_roles",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @OneToOne
    @JoinColumn(name = "id_user",nullable = false,unique = true)
    private User user;

    @OneToMany(mappedBy = "funcionario")
    @ToString.Exclude
    private List<Venda> vendas = new ArrayList();

    @OneToMany(mappedBy = "funcionario")
    @ToString.Exclude
    private List<Compra> compras = new ArrayList();


    public void addVenda(Venda venda) {
        vendas.add(venda);
        venda.setFuncionario(this);
    }

    public void removeVenda(Venda venda) {
        vendas.remove(venda);
        venda.setFuncionario(null);
    }

    public void addCompra(Compra compra) {
        compras.add(compra);
        compra.setFuncionario(this);
    }

    public void removeCompra(Compra compra) {
        compras.remove(compra);
        compra.setFuncionario(null);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
