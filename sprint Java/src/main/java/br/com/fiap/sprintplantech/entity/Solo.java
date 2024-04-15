package br.com.fiap.sprintplantech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_SOLO")
public class Solo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SQ_SOLO")
    @SequenceGenerator(
            name = "SQ_SOLO",
            sequenceName = "SQ_SOLO",
            initialValue = 1,
            allocationSize = 1)

    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO_SOLO")
    private String tipo_solo;

    @Column(name = "PH")
    private Double ph;

    @Column(name = "NIVEL_NITROGENIO")
    private Double nivel_nitrogenio;

    @Column(name = "NIVEL_POTASSIO")
    private Double nivel_potassio;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(
            name = "Fazendas",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(
                    name = "Solo_Fazendas_FK"
            ))
    private Fazendas fazendas;

}
