package br.com.jotavesecurity.ms_sensores.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record SensorEventDTO(

        @NotBlank(message = "The sensor ID is required.")
        String sensorId,

        @NotBlank(message = "The Status is required.")
        @Pattern(regexp = "ABERTO|FECHADO", message = "Status must be ABERTO or FECHADO!")
        String status,

        @NotNull(message = "The timestamp is required.")
        LocalDateTime timestamp
) {
}
