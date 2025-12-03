package br.com.jotavesecurity.mscondominios.dtos;

public record MoradorResponseDTO(

        Long id,
        String nome,
        String cpf,
        ApartamentoDTO apartamento
) {
}
