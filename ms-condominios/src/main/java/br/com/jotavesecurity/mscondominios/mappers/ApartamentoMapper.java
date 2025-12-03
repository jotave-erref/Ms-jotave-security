package br.com.jotavesecurity.mscondominios.mappers;

import br.com.jotavesecurity.mscondominios.dtos.ApartamentoDTO;
import br.com.jotavesecurity.mscondominios.entities.Apartamento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApartamentoMapper {

    // Converte de DTO para Entidade
    public Apartamento toEntity(ApartamentoDTO dto){
        if(dto == null){
            return null;
        }

        return new Apartamento(null, dto.numero(), dto.bloco());
    }

    // Converte de Entidade para DTO
    public ApartamentoDTO toDTO(Apartamento entity){
        if(entity == null){
            return null;
        }
        return new ApartamentoDTO(entity.getId(), entity.getNumero(), entity.getBloco());
    }

    // Converte uma lista de entidades para uma lista de DTO's
    public List<ApartamentoDTO> toDTOList(List<Apartamento> apartamentos){
        return apartamentos.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }
}
