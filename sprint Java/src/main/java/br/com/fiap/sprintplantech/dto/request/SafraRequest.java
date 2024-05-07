package br.com.fiap.sprintplantech.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record SafraRequest(

        @NotNull(message = "Voce deve informar a cultura")
        String cultura,

        @Size(min = 4, max = 4, message = "Deve ser fornecido so o ano")
        @PastOrPresent(message = "Nao pode ser um ano no futuro")
        Long ano,


        @NotNull(message = "A quantidade produzida deve ser informada")
        Double quantidade_produzida,

        @NotNull
        AbstractRequest fazenda

) {
}
