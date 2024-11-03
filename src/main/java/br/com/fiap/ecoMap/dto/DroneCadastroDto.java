package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.enums.DroneStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DroneCadastroDto(
        Long id,

        @NotBlank(message = "O modelo do drone deve ser inserido")
        String modelo,

        DroneStatus status,

        LocalDate dataAquisicao
) {
}