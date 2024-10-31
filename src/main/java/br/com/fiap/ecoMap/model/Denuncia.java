package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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





    @ManyToOne
    @JoinColumn(name = "ID_DENUNCIANTE", referencedColumnName = "ID_USUARIO")
    private Usuario denunciante;
}
