package br.com.jotavesecurity.ms_sensores.clients;

import br.com.jotavesecurity.ms_sensores.dtos.ApartamentoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CondominiosClientFallback implements CondominiosClient{

    // Fallback: Retorna dados genéricos para o sistema não travar
    // Isso permite que o alerta seja gerado mesmo sem saber o bloco exato
    @Override
    public ApartamentoResponseDTO buscarPorId(Long id) {
        return new ApartamentoResponseDTO(id, "N/A", "Desconhecido");
    }
}
