package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.*;

import java.util.Collection;
import java.util.List;

public record AreaMapeadaExibicaoDto(
        Long id,
        String logradouro,
        Double latitude,
        Double longitude,

        Coleta coleta,
        Drone drone,
        List<Residuo> residuos,
        Collection<Denuncia> denuncias
) {
    public AreaMapeadaExibicaoDto(AreaMapeada areaMapeada){
        this(
                areaMapeada.getId(),
                areaMapeada.getLogradouro(),
                areaMapeada.getLatitude(),
                areaMapeada.getLongitude(),

                areaMapeada.getColeta(),
                areaMapeada.getDrone(),
                areaMapeada.getResiduos(),
                areaMapeada.getDenuncias()
        );
    }

}

