package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.*;
import java.util.List;
import java.util.stream.Collectors;

public record AreaMapeadaExibicaoDto(
        Long id,
        String bairro,

        Long idDrone,
        Long idColeta

//        ColetaExibicaoDto coleta,
//        DroneExibicaoDto drone,
//        List<ResiduoExibicaoDto> residuos

) {
    public AreaMapeadaExibicaoDto(AreaMapeada areaMapeada){
        this(
                areaMapeada.getId(),
                areaMapeada.getBairro(),

                areaMapeada.getDrone() != null ? areaMapeada.getDrone().getId() : null,
                areaMapeada.getColeta() != null ? areaMapeada.getColeta().getId() : null


//                areaMapeada.getColeta()!= null ? new ColetaExibicaoDto(areaMapeada.getColeta()) : null,
//                areaMapeada.getDrone()!= null ? new DroneExibicaoDto(areaMapeada.getDrone()) : null,
//                areaMapeada.getResiduos() == null || areaMapeada.getResiduos().isEmpty() ? null :
//                        areaMapeada.getResiduos().stream()
//                        .map(ResiduoExibicaoDto::new)
//                        .collect(Collectors.toList())
        );
    }

}

