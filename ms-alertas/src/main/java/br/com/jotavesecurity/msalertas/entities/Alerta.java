package br.com.jotavesecurity.msalertas.entities;

import br.com.jotavesecurity.msalertas.DTO.AlertaDTO;
import br.com.jotavesecurity.msalertas.enums.AlertaStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "alertas")
@NoArgsConstructor
public class Alerta {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String sensorId;
    private String apartamento;
    private String bloco;
    @Enumerated(EnumType.STRING)
    private AlertaStatus status;
    private LocalDateTime dataHoraOcorrencia;
    private LocalDateTime dataHoraProcessamento;

    @PrePersist
    public void prePersist(){
        this.dataHoraOcorrencia = LocalDateTime.now();
    }

    public static Alerta fromDTO(AlertaDTO dto){
        Alerta alerta = new Alerta();
        alerta.sensorId = dto.sensorId();
        alerta.apartamento = dto.apartamento();
        alerta.bloco = dto.bloco();
        return alerta;
    }
}
