package br.com.fiap.sprintplantech.dto.response;


import lombok.Builder;

@Builder
public record FazendaResponse(

        Long id,

        String nome,

        Double tamanho_hectares,

        String localizacao,

        AgricultorResponse agricultor
) {
}
