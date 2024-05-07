package br.com.fiap.sprintplantech.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UsuarioRequest(

        @Size(min = 3, max = 100)
        @NotNull(message = "O nome de Usuario deve ser informado")
        String nomeUsuario,

        @Size(min = 8, max = 100)
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).*$", message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula, um número e um caractere especial.")
        String senha,

        @Size(min = 10, max = 100)
        @NotNull(message = "Seu nome completo deve ser informado")
        String nomeCompleto,

        @Email(message = "O formato do email está incorreto")
        @NotNull(message = "O email deve ser informado")
        String email,

        @PastOrPresent
        @NotNull(message = "A data nao pode ser nula")
        LocalDate dataCriacao,

        AbstractRequest agricultor
) {
}
