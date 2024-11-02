package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Residuo;


public record ResiduoExibicaoDto(
        Long id,
        String tipo,
        String descricao,
        Long quantidade
) {
    public ResiduoExibicaoDto(Residuo residuo){
        this(
                residuo.getId(),
                residuo.getTipo(),
                residuo.getDescricao(),
                residuo.getQuantidade()
        );
    }
}
