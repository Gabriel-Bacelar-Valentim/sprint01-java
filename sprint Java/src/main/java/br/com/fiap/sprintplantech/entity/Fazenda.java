package br.com.fiap.sprintplantech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_FAZENDAS")
public class Fazenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SQ_FAZENDAS")
    @SequenceGenerator(
            name = "SQ_FAZENDAS",
            sequenceName = "SQ_FAZENDAS",
            initialValue = 1,
            allocationSize = 1)

    @Column(name = "ID_FAZENDAS")
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
            referencedColumnName = "ID_FAZENDAS",
            foreignKey = @ForeignKey(
                    name = "Fazendas_Agricultores_FK"
            ))
            private Agricultor agricultor;

}
