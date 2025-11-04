package br.com.jotavesecurity.ms_condominios.dtos;

import jakarta.validation.constraints.NotBlank;

public record ApartamentoDTO(
        Long id,

        @NotBlank(message= "O numero do apartamento não pode ser vazio")
        String numero,

        String bloco
) {
}
