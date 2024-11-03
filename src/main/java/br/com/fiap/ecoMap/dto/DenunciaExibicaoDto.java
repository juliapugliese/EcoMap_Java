package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.AreaMapeada;
import br.com.fiap.ecoMap.model.Denuncia;
import br.com.fiap.ecoMap.model.Usuario;
import br.com.fiap.ecoMap.model.enums.DenunciaStatus;

import java.time.LocalDate;
import java.util.List;

public record DenunciaExibicaoDto(
        Long id,
        String descricao,
        LocalDate dataSolicitacao,
        DenunciaStatus status,

        List<AreaMapeada> areas,
        Usuario denunciante
) {
    public DenunciaExibicaoDto(Denuncia denuncia){
        this(
                denuncia.getId(),
                denuncia.getDescricao(),
                denuncia.getDataSolicitacao(),
                denuncia.getStatus(),

                denuncia.getAreas(),
                denuncia.getDenunciante()
        );
    }
}
