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
@Table(name = "TB_FAZENDAS")
public class Fazendas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SQ_FAZENDAS")
    @SequenceGenerator(
            name = "SQ_FAZENDAS",
            sequenceName = "SQ_FAZENDAS",
            initialValue = 1,
            allocationSize = 1)

    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TAMANHO_HECTARES")
    private Double tamanho_hectares;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(
            name = "AGRICULTORES",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(
                    name = "Fazendas_Agricultores_FK"
            ))
            private Agricultores agricultores;

}
