package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Drone;
import br.com.fiap.ecoMap.model.enums.DroneStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DroneExibicaoDto(
        Long id,
        String modelo,
        DroneStatus status,
        LocalDate dataAquisicao,

        List<AreaMapeadaExibicaoDto> areas
) {
    public DroneExibicaoDto(Drone drone){
        this(
                drone.getId(),
                drone.getModelo(),
                drone.getStatus(),
                drone.getDataAquisicao(),

                drone.getAreas() == null || drone.getAreas().isEmpty() ? null :
                        drone.getAreas().stream()
                                .map(AreaMapeadaExibicaoDto::new)
                                .collect(Collectors.toList())
        );
    }
}
