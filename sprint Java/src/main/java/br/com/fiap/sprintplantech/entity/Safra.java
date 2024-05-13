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
@Table(name = "TB_SAFRAS")
public class Safra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SQ_SAFRAS")
    @SequenceGenerator(
            name = "SQ_SAFRAS",
            sequenceName = "SQ_SAFRAS",
            initialValue = 1,
            allocationSize = 1)

    @Column(name = "ID_SAFRAS")
    private Long id;

    @Column(name = "CULTURA")
    private String cultura;

    @Column(name = "ANO")
    private Long ano;

    @Column(name = "QUANTIDADE_PRODUZIDA")
    private Double quantidade_produzida;


    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(
            name = "Fazendas",
            referencedColumnName = "ID_FAZENDAS",
            foreignKey = @ForeignKey(
                    name = "Safras_Fazendas_FK"
            ))
    private Fazenda fazenda;
}
