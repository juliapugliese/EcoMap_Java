package br.com.fiap.ecoMap.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResiduoCadastroDto(
        Long id,

        @NotBlank(message = "O tipo de resíduo é obrigatório")
        String tipo,

        @NotNull(message = "A quantidade de resíduos é obrigatória")
        @Min(value = 0, message = "A quantidade deve ser maior que 0.")
        Long quantidade,

        @NotNull(message = "O resíduo deve estar vinculado a uma área")
        String bairro
) {
}
