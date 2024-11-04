package br.com.fiap.ecoMap.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LocalizacaoCadastroDto(
        Long id,

        @NotBlank(message = "O endereço é obrigatório!")
        String endereco,

        @Pattern(regexp = "^(\\d{5}-\\d{3}|\\d{8})$", message = "O CEP deve estar no formato 12345-678 ou 12345678")
        String cep,

        @NotBlank(message = "As coordenadas são obrigatórias!")
        String coordenadas,

        @NotBlank(message = "O bairro deve ser enviado!")
        String bairro
) {
}
