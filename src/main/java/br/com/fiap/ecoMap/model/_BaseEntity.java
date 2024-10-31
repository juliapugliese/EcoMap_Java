package br.com.fiap.ecoMap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.StringJoiner;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
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
    private int id;

}
