package br.com.fiap.sprintplantech.dto.response;

import lombok.Builder;

import java.time.LocalDate;


@Builder
public record UsuarioResponse(

        Long id,

        String nomeUsuario,

        String senha,

        String nomeCompleto,

        String email,

        LocalDate dataCriacao,

        AgricultorResponse agricultor

) {
}
