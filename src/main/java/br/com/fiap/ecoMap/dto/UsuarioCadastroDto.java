package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(
        Long usuarioId,

        @NotBlank(message = "O nome do usuário é obrigatório")
        String nome,

        @NotBlank(message = "O e-mail do usuário é obrigatório")
        @Email(message = "O e-mail inserido não é valido")
        String email,

        @NotBlank(message = "O cep é obrigatório")
        @Pattern(regexp = "^(\\d{5}-\\d{3}|\\d{8})$", message = "O CEP deve estar no formato 12345-678 ou 12345678")
        String cep,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres")
        String senha,

        UsuarioRole role
) {
}
