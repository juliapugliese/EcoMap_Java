package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_RESIDUOS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "ID_RESIDUO"))
public class Residuo extends _BaseEntity {

    private String tipo;
    private String descricao;

    @Transient
    private Long quantidade;
}
