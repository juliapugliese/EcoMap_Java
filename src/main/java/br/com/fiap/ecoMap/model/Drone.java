package br.com.fiap.ecoMap.model;

import br.com.fiap.ecoMap.model.enums.DenunciaStatus;
import br.com.fiap.ecoMap.model.enums.DroneStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "T_DRONES")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @PrePersist
    public void prePersist() {
        this.status = DroneStatus.ATIVO;
    }

}
