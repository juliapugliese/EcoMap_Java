package br.com.fiap.ecoMap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ColetaCadastroDto(
        Long id,


        @NotNull(message = "A quantidade de resíduos é obrigatória")
        Long quantidadeResiduo,

        @NotNull(message = "A data da coleta é obrigatória!")
        LocalDate dataColeta

) {

}
