package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Drone;
import br.com.fiap.ecoMap.model.enums.DroneStatus;

import java.time.LocalDate;

public record DroneExibicaoDto(
        Long id,
        String modelo,
        DroneStatus status,
        LocalDate dataAquisicao
) {
    public DroneExibicaoDto(Drone drone){
        this(
                drone.getId(),
                drone.getModelo(),
                drone.getStatus(),
                drone.getDataAquisicao()
        );
    }
}
