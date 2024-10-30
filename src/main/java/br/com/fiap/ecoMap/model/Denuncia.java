package br.com.fiap.ecoMap.model;

import java.time.LocalDate;

public class Denuncia {
    private Long id;
    private LocalDate dataSolicitacao;
    private DenunciaStatus status;
    private String descricao;

    private Usuario denunciante;
}
