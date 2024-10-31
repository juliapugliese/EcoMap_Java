package br.com.fiap.ecoMap.model;
import jakarta.persistence.*;
import lombok.*;



@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class _BaseEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ID"
    )
    @SequenceGenerator(
            name = "SEQ_ID",
            sequenceName = "SEQ_ID",
            allocationSize = 1
    )
    private Long id;

}
