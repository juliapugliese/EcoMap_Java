package br.com.fiap.ecoMap.model;
import jakarta.persistence.*;
import lombok.*;



@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class _BaseEntity {
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "SEQ_ID"
//    )
//    @SequenceGenerator(
//            name = "SEQ_ID",
//            sequenceName = "SEQ_ID",
//            allocationSize = 1
//    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
