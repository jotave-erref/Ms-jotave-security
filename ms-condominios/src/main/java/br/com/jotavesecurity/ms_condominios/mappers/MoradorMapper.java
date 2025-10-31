package br.com.jotavesecurity.ms_condominios.mappers;

import br.com.jotavesecurity.ms_condominios.dtos.MoradorRequestDTO;
import br.com.jotavesecurity.ms_condominios.dtos.MoradorResponseDTO;
import br.com.jotavesecurity.ms_condominios.entities.Apartamento;
import br.com.jotavesecurity.ms_condominios.entities.Morador;
import org.springframework.stereotype.Component;

@Component
public class MoradorMapper {

    private final ApartamentoMapper apartamentoMapper = new ApartamentoMapper();

    public Morador toEntity(MoradorRequestDTO dto){
        var morador = new Morador();
        morador.setNome(dto.nome());
        morador.setCpf(dto.cpf());

        // O Mapper não acessa o banco!
        // Apenas uma referência do apartamento com o ID.
        // O Service será responsável por validar e buscar a entidade completa.
        var apartamento = new Apartamento();
        apartamento.setId(dto.apartamentoId());
        morador.setApartamento(apartamento);

        return morador;
    }

    public MoradorResponseDTO toDTO(Morador entity){
        return new MoradorResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                apartamentoMapper.toDTO(entity.getApartamento())
        );

    }
}
