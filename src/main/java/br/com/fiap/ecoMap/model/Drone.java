package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "DRONES")
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
}
