package br.com.fiap.sprintplantech.dto.response;

import br.com.fiap.sprintplantech.dto.request.AbstractRequest;
import lombok.Builder;

import java.util.Date;

@Builder
public record DadoClimaticoResponse(

        Long id,

        Date data,

        Double temperatura_media,

        Double umidade_relativa,

        Long precipitacao,

        FazendaResponse fazenda

) {
}
