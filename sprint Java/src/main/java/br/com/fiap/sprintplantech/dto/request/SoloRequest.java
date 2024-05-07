package br.com.fiap.sprintplantech.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record SoloRequest(

        @NotNull(message = "Voce deve informar o(s) tipo(s) de solo(s) de sua fazenda")
        String tipo_solo,

        @NotNull(message = "Voce deve informar o ph do solo")
        @DecimalMin(value = "0.01", message = "O ph deve ser maior que zero")
        @DecimalMax(value = "15.00", message = "O ph deve ser menor que quatorze")
        Double ph,

        @NotNull(message = "Voce deve informar o nivel de nitrogenio do solo")
        @DecimalMin(value = "0.01", message = "O nitrogenio deve ser maior que zero")
        Double nivel_nitrogenio,

        @NotNull(message = "Voce deve informanar o nivel de potassio do solo")
        @DecimalMin(value = "0.01", message = "O potassio deve ser maior que zero")
        @DecimalMax(value = "120.00", message = "O ph deve ser menor que cento e vinte")
        Double nivel_potassio,

        @NotNull
        AbstractRequest fazenda

) {
}
