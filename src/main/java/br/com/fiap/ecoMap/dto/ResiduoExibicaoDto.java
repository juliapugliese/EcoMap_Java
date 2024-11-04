package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Residuo;


public record ResiduoExibicaoDto(
        Long id,
        String tipo,
        Long quantidade,

        AreaMapeadaExibicaoDto areaMapeada
) {
    public ResiduoExibicaoDto(Residuo residuo){
        this(
                residuo.getId(),
                residuo.getTipo(),
                residuo.getQuantidade(),

                residuo.getAreaMapeada()!= null ? new AreaMapeadaExibicaoDto(residuo.getAreaMapeada()) : null

        );
    }
}
