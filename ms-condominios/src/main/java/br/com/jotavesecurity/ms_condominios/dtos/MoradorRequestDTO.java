package br.com.jotavesecurity.ms_condominios.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MoradorDTO(

        @NotBlank
        String nome,

        @NotBlank
        @Size(min = 11, max = 11)
        String cpf,

        @NotBlank
        Long apartamentoId

) {
}
