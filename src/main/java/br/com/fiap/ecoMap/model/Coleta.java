package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "T_COLETAS")
@SecondaryTable(name = "T_AREA_MAPEADA")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "ID_COLETA"))
public class Coleta extends _BaseEntity{

    @Transient
    private AreaMapeada localizacao;

    @Embedded
    @AttributeOverride(name = "quantidade", column = @Column(name = "QUANTIDADE_RESIDUO"))
    private Residuo residuo;

    @Column(name = "DATA_COLETA")
    private LocalDate dataColeta;

    @OneToMany(mappedBy = "coleta")// Nome do atributo na classe AreaMapeada
    private List<AreaMapeada> areas;

}
