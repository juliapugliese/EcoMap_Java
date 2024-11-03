package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Coleta;
import java.time.LocalDate;
import java.util.List;

public record ColetaExibicaoDto(
        Long id,
        Long quantidadeResiduo,
        LocalDate dataColeta,

        List<AreaMapeada> areas
) {
    public ColetaExibicaoDto(Coleta coleta){
        this(
                coleta.getId(),
                coleta.getQuantidadeResiduo(),
                coleta.getDataColeta(),

                coleta.getAreas()
        );
    }
}
