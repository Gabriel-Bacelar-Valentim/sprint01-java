package br.com.fiap.sprintplantech.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FazendaRequest(

        @Size(min = 3, max = 100)
        @NotNull(message = "O nome da fazenda deve ser informado")
        String nome,

        @NotNull(message = "O tamanho deve ser informado")
        @DecimalMin(value = "0.01", message = "O tamanho deve ser maior que zero")
        Double tamanho_hectares,

        @NotNull(message = "Voce deve informar a localizacao de sua fazenda")
        String localizacao,

        @NotNull
        AbstractRequest agricultor

) {
}
