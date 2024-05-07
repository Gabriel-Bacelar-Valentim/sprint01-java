package br.com.fiap.sprintplantech.dto.response;


import lombok.Builder;

@Builder
public record SoloResponse(

        Long id,

        String tipo_solo,

        Double ph,

        Double nivel_nitrogenio,

        Double nivel_potassio,

        FazendaResponse fazenda
) {
}
