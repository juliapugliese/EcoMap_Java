package br.com.fiap.ecoMap.model;

import br.com.fiap.ecoMap.model.enums.DenunciaStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_SOLICITACOES")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "ID_LOCALIZACAO", referencedColumnName = "ID_LOCALIZACAO")
    private Localizacao localizacao;


    @PrePersist
    public void prePersist() {
        if (this.dataSolicitacao == null) {
            this.dataSolicitacao = LocalDate.now();
        }
        if (this.status == null) {
            this.status = DenunciaStatus.PENDENTE;
        }
    }

}
