package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RESIDUOS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AttributeOverride(name = "id", column = @Column(name = "ID_RESIDUO"))
public class Residuo extends _BaseEntity {

    private String tipo;
    private String descricao;

    @Transient
    private Long quantidade;
}
