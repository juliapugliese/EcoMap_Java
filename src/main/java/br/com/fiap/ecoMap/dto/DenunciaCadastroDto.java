package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.enums.DenunciaStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DenunciaCadastroDto(
        Long id,

        @NotNull(message = "A descrição da solicitação é obrigatória!")
        String descricao,

        LocalDate dataSolicitacao,

        DenunciaStatus status,

        Long idDenunciante
) {
}
