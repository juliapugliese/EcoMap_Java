package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.AreaMapeada;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AreaMapeadaCadastroDto(
        Long id,

        @NotBlank(message = "A localização é obrigatório!")
        String logradouro,

        @NotNull(message = "A latitude é obrigatória!")
        Double latitude,

        @NotNull(message = "A longitude é obrigatória!")
        Double longitude,

        Long idDrone,

        Long idColeta
) {}
