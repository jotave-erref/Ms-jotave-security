package br.com.jotavesecurity.ms_alertas.DTO;

import java.time.LocalDateTime;

public record AlertaDTO(
        String sensorId,
        String apartamento,
        String bloco,
        LocalDateTime timestamp
) {
}
