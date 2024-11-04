package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Localizacao;

public record LocalizacaoExibicaoDto(
        Long id,
        String endereco,
        String cep,
        String coordenadas,

        AreaMapeada area
) {
    public LocalizacaoExibicaoDto(Localizacao localizacao){
        this(
                localizacao.getId(),
                localizacao.getEndereco(),
                localizacao.getCep(),
                localizacao.getCoordenadas(),

                localizacao.getAreaMapeada()
        );
    }
}
