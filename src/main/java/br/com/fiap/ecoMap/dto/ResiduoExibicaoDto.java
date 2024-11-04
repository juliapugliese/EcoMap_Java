package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Residuo;


public record ResiduoExibicaoDto(
        Long id,
        String tipo,
        Long quantidade,

        AreaMapeada areaMapeada
) {
    public ResiduoExibicaoDto(Residuo residuo){
        this(
                residuo.getId(),
                residuo.getTipo(),
                residuo.getQuantidade(),

                residuo.getAreaMapeada()
        );
    }
}
