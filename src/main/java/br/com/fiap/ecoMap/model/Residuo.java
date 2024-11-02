package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_RESIDUOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "ID_RESIDUO"))
public class Residuo extends _BaseEntity {

    @Column(name = "TIPO_RESIDUO")
    private String tipo;

    private String descricao;

    @Column(name = "QUANTIDADE")
    private Long quantidade;

    @ManyToOne
    @JoinColumn(name = "ID_AREA_MAPEADA", referencedColumnName = "ID_AREA")//name = Chave estrangeira na tabela T_RESIDUOS
    private AreaMapeada areaMapeada;

}
