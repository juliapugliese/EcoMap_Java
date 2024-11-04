package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "T_LOCALIZACAO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "ID_LOCALIZACAO"))
public class Localizacao extends _BaseEntity {

    private String cep;

    private String endereco;

    private String coordenadas;

    @ManyToOne
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID_AREA")
    private AreaMapeada areaMapeada;




    @OneToMany(mappedBy = "localizacao", fetch = FetchType.LAZY)
    private List<Denuncia> denuncias = new ArrayList<>();



}