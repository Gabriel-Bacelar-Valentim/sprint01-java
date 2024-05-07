package br.com.fiap.sprintplantech.entity;

import br.com.fiap.sprintplantech.dto.request.AgricultorRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIOS")
    @SequenceGenerator(name = "SQ_USUARIOS", sequenceName = "SQ_USUARIOS", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOME_USUARIO")
    private String nomeUsuario;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "AGRICULTOR_ID", referencedColumnName = "ID_AGRICULTORES")
    private Agricultor agricultor;
}
