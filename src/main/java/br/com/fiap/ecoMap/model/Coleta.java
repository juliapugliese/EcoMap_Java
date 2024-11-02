package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "coleta")// Nome do atributo na classe AreaMapeada
    private List<AreaMapeada> areas;




    public void recalculateQuantidadeResiduo() {
        if (areas != null && !areas.isEmpty()) {
            long totalQuantity = areas.stream()
                    .map(AreaMapeada::getResiduo)
                    .filter(Objects::nonNull)
                    .mapToLong(Residuo::getQuantidade)
                    .sum();

            this.quantidadeResiduo = totalQuantity;
        } else {
            this.quantidadeResiduo = null;
        }
    }

}
