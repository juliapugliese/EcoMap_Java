package br.com.fiap.ecoMap.model;

import br.com.fiap.ecoMap.model.enums.DenunciaStatus;
import br.com.fiap.ecoMap.model.enums.DroneStatus;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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


    @ManyToMany(mappedBy = "denuncias", fetch = FetchType.LAZY)
    private List<AreaMapeada> areas;

    @ManyToOne
    @JoinColumn(name = "ID_DENUNCIANTE", referencedColumnName = "ID_USUARIO")
    private Usuario denunciante;

    @PostConstruct
    public void init() {
        if (this.dataSolicitacao == null) {
            this.dataSolicitacao =  LocalDate.now();
        } else if (this.status == null) {
            this.status = DenunciaStatus.PENDENTE;
        }
    }

}
