package br.com.jotavesecurity.msalertas.DTO;

import java.time.LocalDateTime;

public record AlertaDTO(
        String sensorId,
        String apartamento,
        String bloco,
        LocalDateTime timestamp
) {
}
