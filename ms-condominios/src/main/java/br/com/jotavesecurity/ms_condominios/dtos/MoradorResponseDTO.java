package br.com.jotavesecurity.ms_condominios.dtos;

public record MoradorResponseDTO(

        Long id,
        String nome,
        String cpf,
        ApartamentoDTO apartamento
) {
}
