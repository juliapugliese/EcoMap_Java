package br.com.fiap.ecoMap.dto;


import jakarta.validation.constraints.NotBlank;

public record AreaMapeadaCadastroDto(
        Long id,

        @NotBlank(message = "O bairro é obrigatório!")
        String bairro,

        Long idDrone,

        Long idColeta
) {}
