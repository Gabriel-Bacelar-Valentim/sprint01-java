package br.com.fiap.sprintplantech.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record DadoClimaticoRequest(

        @PastOrPresent(message = "Nao aceitamos data do futuro")
        @NotNull(message = "A data deve ser informada")
        Date data,

        @NotNull(message = "A temperatura nao pode ser nula")
        @DecimalMin(value = "0.01", message = "A temperatura deve ser maior que zero")
        Double temperatura_media,

        @NotNull(message = "A umidade nao pode ser nula")
        Double umidade_relativa,

        @NotNull(message = "O nivel de precipitacao nao pode ser nulo")
        @Positive(message = "Nao pode ser abaixo de zero")
        Long precipitacao,

        @NotNull
        AbstractRequest fazenda

) {
}
