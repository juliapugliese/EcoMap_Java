package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.AreaMapeada;

public record AreaMapeadaExibicaoDto(
        Long id,
        String logradouro,
        Double latitude,
        Double longitude
) {
    public AreaMapeadaExibicaoDto(AreaMapeada areaMapeada){
        this(
                areaMapeada.getId(),
                areaMapeada.getLogradouro(),
                areaMapeada.getLatitude(),
                areaMapeada.getLongitude()
        );
    }

}

