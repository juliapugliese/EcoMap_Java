package br.com.fiap.ecoMap.dto;

import jakarta.validation.constraints.NotNull;

public record AreaMapeadaCadastroDto(
        Long id,

        @NotNull(message = "O bairro é obrigatório!")
        String bairro,

        Long idDrone,

        Long idColeta
) {}
