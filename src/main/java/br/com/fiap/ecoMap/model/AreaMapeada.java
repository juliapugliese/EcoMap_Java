package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "T_AREA_MAPEADA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "ID_AREA"))
public class AreaMapeada extends _BaseEntity {

    @Column(name = "NOME_AREA", nullable = false)
    private String logradouro;

    private Double latitude;

    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "ID_DRONE", referencedColumnName = "ID_DRONE")
    //name = Chave estrangeira na tabela T_AREA_MAPEADA
    private Drone drone;

    @ManyToOne
    @JoinColumn(name = "ID_COLETA", referencedColumnName = "ID_COLETA")
    private Coleta coleta;

    @OneToMany(mappedBy = "areaMapeada")// Nome do atributo na classe Residuo
    private List<Residuo> residuos;

    @ManyToMany
    @JoinTable(name = "T_AREA_SOLICITACAO",
            joinColumns = @JoinColumn(name = "ID_AREA", referencedColumnName = "ID_AREA"),
            inverseJoinColumns = @JoinColumn(name = "ID_SOLICITACAO", referencedColumnName = "ID_SOLICITACAO")
    )
    private Collection<Denuncia> denuncias;
}
