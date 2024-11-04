package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Denuncia;
import br.com.fiap.ecoMap.model.enums.DenunciaStatus;

import java.time.LocalDate;

public record DenunciaExibicaoDto(
        Long id,
        String descricao,
        LocalDate dataSolicitacao,
        DenunciaStatus status,

        LocalizacaoExibicaoDto localizacao,
        UsuarioExibicaoDto denunciante
) {
    public DenunciaExibicaoDto(Denuncia denuncia){
        this(
                denuncia.getId(),
                denuncia.getDescricao(),
                denuncia.getDataSolicitacao(),
                denuncia.getStatus(),

                denuncia.getLocalizacao()!= null ? new LocalizacaoExibicaoDto(denuncia.getLocalizacao()) : null,
                denuncia.getDenunciante() != null ? new UsuarioExibicaoDto(denuncia.getDenunciante()) : null
        );
    }
}
