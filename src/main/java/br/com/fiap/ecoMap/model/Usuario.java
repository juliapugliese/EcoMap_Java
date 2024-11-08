package br.com.fiap.ecoMap.model;

import br.com.fiap.ecoMap.model.enums.UsuarioRole;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "T_USUARIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "ID_USUARIO"))
public class Usuario extends _BaseEntity implements UserDetails {
    private String nome;
    private String cep;

    @Email
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO")
    private UsuarioRole role;



    @OneToMany(mappedBy = "denunciante", fetch = FetchType.EAGER)
    private List<Denuncia> denuncias;

    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = UsuarioRole.USER;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioRole.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
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
