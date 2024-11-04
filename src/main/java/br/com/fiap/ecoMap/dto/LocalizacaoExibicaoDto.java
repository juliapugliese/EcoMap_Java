package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Localizacao;

public record LocalizacaoExibicaoDto(
        Long id,
        String endereco,
        String cep,
        String coordenadas,

        AreaMapeadaExibicaoDto area
) {
    public LocalizacaoExibicaoDto(Localizacao localizacao){
        this(
                localizacao.getId(),
                localizacao.getEndereco(),
                localizacao.getCep(),
                localizacao.getCoordenadas(),

                localizacao.getAreaMapeada()!= null ? new AreaMapeadaExibicaoDto(localizacao.getAreaMapeada()) : null
        );
    }
}
