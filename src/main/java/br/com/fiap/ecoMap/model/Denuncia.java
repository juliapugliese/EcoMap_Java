package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "T_SOLICITACOES")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "ID_SOLICITACAO"))
public class Denuncia extends _BaseEntity {

    private String descricao;

    @Column(name = "DATA_SOLICITACAO")
    private LocalDate dataSolicitacao;

    @Enumerated(EnumType.STRING)
    private DenunciaStatus status;


    @ManyToMany(mappedBy = "denuncias", fetch = FetchType.LAZY)
    private List<AreaMapeada> areas;

    @ManyToOne
    @JoinColumn(name = "ID_DENUNCIANTE", referencedColumnName = "ID_USUARIO")
    private Usuario denunciante;
}
