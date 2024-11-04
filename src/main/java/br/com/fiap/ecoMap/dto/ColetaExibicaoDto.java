package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Coleta;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ColetaExibicaoDto(
        Long id,
        Long quantidadeResiduo,
        LocalDate dataColeta,

        List<AreaMapeadaExibicaoDto> areas
) {
    public ColetaExibicaoDto(Coleta coleta){
        this(
                coleta.getId(),
                coleta.getQuantidadeResiduo(),
                coleta.getDataColeta(),

                coleta.getAreas() == null || coleta.getAreas().isEmpty() ? null :
                        coleta.getAreas().stream()
                                .map(AreaMapeadaExibicaoDto::new)
                                .collect(Collectors.toList())
        );
    }
}
