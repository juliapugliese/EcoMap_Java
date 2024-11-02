package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Coleta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ColetaExibicaoDto(
        Long id,
        Long quantidadeResiduo,
        LocalDate dataColeta
) {
    public ColetaExibicaoDto(Coleta coleta){
        this(
                coleta.getId(),
                coleta.getQuantidadeResiduo(),
                coleta.getDataColeta()
        );
    }
}
