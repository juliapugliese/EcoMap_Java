package br.com.fiap.ecoMap.dto;

import br.com.fiap.ecoMap.model.Denuncia;
import br.com.fiap.ecoMap.model.enums.DenunciaStatus;

import java.time.LocalDate;

public record DenunciaExibicaoDto(
        Long id,
        String descricao,
        LocalDate dataSolicitacao,
        DenunciaStatus status
) {
    public DenunciaExibicaoDto(Denuncia denuncia){
        this(
                denuncia.getId(),
                denuncia.getDescricao(),
                denuncia.getDataSolicitacao(),
                denuncia.getStatus()
        );
    }
}
