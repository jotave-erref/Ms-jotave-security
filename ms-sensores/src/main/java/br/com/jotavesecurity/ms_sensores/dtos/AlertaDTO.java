package br.com.jotavesecurity.ms_sensores.dtos;

import java.time.LocalDateTime;

public record AlertaDTO(
        String sensorId,
        String apartamento,
        String bloco,
        LocalDateTime timestamp
) {
}
