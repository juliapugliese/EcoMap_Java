package br.com.fiap.ecoMap.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record ColetaCadastroDto(
        Long id,

        Long quantidadeResiduo,

        @NotNull(message = "A data da coleta é obrigatória!")
        LocalDate dataColeta,

        List<Long> idAreas

) {

}
