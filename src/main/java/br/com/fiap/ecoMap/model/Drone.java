package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "T_DRONES")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "ID_DRONE"))
public class Drone extends _BaseEntity {
    private String modelo;

    @Enumerated(EnumType.STRING)
    private DroneStatus status;

    @Column(name = "DATA_AQUISICAO")
    private LocalDate dataAquisicao;

    @OneToMany(mappedBy = "drone")// Nome do atributo na classe AreaMapeada
    private List<AreaMapeada> areas;

}
