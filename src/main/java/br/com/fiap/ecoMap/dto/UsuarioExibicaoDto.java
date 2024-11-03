package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Denuncia;
import br.com.fiap.ecoMap.model.Usuario;
import br.com.fiap.ecoMap.model.enums.UsuarioRole;

import java.util.List;

public record UsuarioExibicaoDto(
        Long usuarioId,
        String nome,
        String email,
        UsuarioRole role,

        List<Denuncia> denuncias
) {
    public UsuarioExibicaoDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),

                usuario.getDenuncias()
        );
    }
}
