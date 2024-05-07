package br.com.fiap.sprintplantech.dto.response;


import lombok.Builder;

@Builder
public record SafraResponse(


        Long id,

        String cultura,

        Long ano,

        Double quantidade_produzida,

        FazendaResponse fazenda
) {
}
