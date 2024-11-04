package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "T_COLETAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "ID_COLETA"))
public class Coleta extends _BaseEntity{

    @Column(name = "QUANTIDADE_RESIDUO")
    private Long quantidadeResiduo;

    @Column(name = "DATA_COLETA")
    private LocalDate dataColeta;

    @OneToMany(mappedBy = "coleta", fetch = FetchType.EAGER)// Nome do atributo na classe AreaMapeada
    private List<AreaMapeada> areas;



//teste
//    public void updateQuantidadeResiduo() {
//        if (areas != null && !areas.isEmpty()) {
//
//            this.quantidadeResiduo = areas.stream()
//                    .filter(area -> area.getResiduos() != null)
//                    .mapToLong(area -> area.getResiduos().stream()
//                            .filter(residuo -> residuo != null)
//                            .mapToLong(Residuo::getQuantidade)
//                            .sum())
//                    .sum();
//        } else {
//            this.quantidadeResiduo = null;
//        }
//    }

}
