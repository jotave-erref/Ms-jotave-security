package br.com.jotavesecurity.mscondominios.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MoradorRequestDTO(

        @NotBlank
        String nome,

        @NotBlank
        @Size(min = 11, max = 11)
        String cpf,

        @NotNull
        Long apartamentoId

) {
}
