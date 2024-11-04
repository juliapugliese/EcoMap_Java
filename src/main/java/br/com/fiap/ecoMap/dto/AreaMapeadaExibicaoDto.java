package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.*;

import java.util.Collection;
import java.util.List;

public record AreaMapeadaExibicaoDto(
        Long id,
        String bairro,

        Coleta coleta,
        Drone drone,
        List<Residuo> residuos

) {
    public AreaMapeadaExibicaoDto(AreaMapeada areaMapeada){
        this(
                areaMapeada.getId(),
                areaMapeada.getBairro(),

                areaMapeada.getColeta(),
                areaMapeada.getDrone(),
                areaMapeada.getResiduos()
        );
    }

}

