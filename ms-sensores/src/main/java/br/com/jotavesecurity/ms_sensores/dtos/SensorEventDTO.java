package br.com.jotavesecurity.ms_sensores.dtos;

import java.time.LocalDateTime;

public record SensorEventDTO(

        String sensorId,
        String status,
        LocalDateTime timestamp
) {
}
