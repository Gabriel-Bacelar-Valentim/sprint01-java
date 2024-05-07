package br.com.fiap.sprintplantech.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AgricultorRequest(

        @Size(min = 3, max = 100)
        @NotNull(message = "O nome do agricultor deve ser informado")
        String nome,

        @Size(min = 3, max = 100)
        @NotNull(message = "O nome da cidade deve ser informado")
        String cidade,

        @Size(min = 3, max = 100)
        @NotNull(message = "O estado deve ser informado")
        String estado,

        @Size(min = 11, max = 15)
        @NotNull(message = "O telefone deve ser informado")
        String telefone,

        @Email(message = "O formato do email está incorreto")
        @NotNull(message = "O email deve ser informado")
        String email
) {
}
