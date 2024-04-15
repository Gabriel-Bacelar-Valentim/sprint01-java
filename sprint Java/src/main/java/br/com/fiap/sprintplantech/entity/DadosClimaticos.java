package br.com.fiap.sprintplantech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_DADOS_CLIMATICOS")
public class DadosClimaticos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SQ_DADOS_CLIMATICOS")
    @SequenceGenerator(
            name = "SQ_DADOS_CLIMATICOS",
            sequenceName = "SQ_DADOS_CLIMATICOS",
            initialValue = 1,
            allocationSize = 1)

    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA")
    private Date data;

    @Column(name = "TEMPERATURA_MEDIA")
    private Double temperatura_media;

    @Column(name = "UMIDADE_RELATIVA")
    private Double umidade_relativa;

    @Column(name = "PRECIPITACAO")
    private Long precipitacao;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(
            name = "Fazendas",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(
                    name = "dadosClimaticos_Fazendas_FK"
            ))
    private Fazendas fazendas;
}
