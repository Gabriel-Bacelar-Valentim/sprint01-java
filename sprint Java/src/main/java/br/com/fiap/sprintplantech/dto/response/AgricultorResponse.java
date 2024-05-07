package br.com.fiap.sprintplantech.dto.response;

import lombok.Builder;

@Builder
public record AgricultorResponse(

        Long id,

        String nome,

        String cidade,

        String estado,

        String telefone,

        String email
) {
}
