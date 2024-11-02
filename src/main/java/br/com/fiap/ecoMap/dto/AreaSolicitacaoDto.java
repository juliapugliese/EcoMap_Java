package br.com.fiap.ecoMap.dto;

import jakarta.validation.constraints.NotNull;

public record AreaSolicitacaoDto(
        @NotNull(message = "O id do área é obrigatório")
        Long idArea,

        @NotNull(message = "O id da denúncia é obrigatório")
        Long idDenuncia

) {
}
