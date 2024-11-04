package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Usuario;
import br.com.fiap.ecoMap.model.enums.UsuarioRole;
import java.util.List;
import java.util.stream.Collectors;

public record UsuarioExibicaoDto(
        Long usuarioId,
        String nome,
        String email,
        UsuarioRole role

//        List<DenunciaExibicaoDto> denuncias
) {
    public UsuarioExibicaoDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()

//                usuario.getDenuncias() == null || usuario.getDenuncias().isEmpty() ? null :
//                        usuario.getDenuncias().stream()
//                                .map(DenunciaExibicaoDto::new)
//                                .collect(Collectors.toList())
        );
    }
}
