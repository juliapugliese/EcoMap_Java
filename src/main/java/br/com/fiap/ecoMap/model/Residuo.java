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

    @Column(name = "TIPO_RESIDUO")
    private String tipo;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_AREA_MAPEADA", referencedColumnName = "ID_AREA")//name = Chave estrangeira na tabela T_RESIDUOS
    private AreaMapeada areaMapeada;

    @Transient
    private Long quantidade;
}
