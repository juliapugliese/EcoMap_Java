package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_AREA_MAPEADA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "ID_AREA"))
public class AreaMapeada extends _BaseEntity {

    private String bairro;

    @ManyToOne
    @JoinColumn(name = "ID_DRONE", referencedColumnName = "ID_DRONE")
    private Drone drone;

    @ManyToOne
    @JoinColumn(name = "ID_COLETA", referencedColumnName = "ID_COLETA")
    private Coleta coleta;


    @OneToMany(mappedBy = "areaMapeada", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Localizacao> localizacoes = new ArrayList<>();

    @OneToMany(mappedBy = "areaMapeada", fetch = FetchType.EAGER)// Nome do atributo na classe Residuo
    private List<Residuo> residuos;

}
