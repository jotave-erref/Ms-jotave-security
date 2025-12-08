package br.com.jotavesecurity.msalertas.service;

import br.com.jotavesecurity.msalertas.DTO.AlertaDTO;
import br.com.jotavesecurity.msalertas.entities.Alerta;
import br.com.jotavesecurity.msalertas.enums.AlertaStatus;
import br.com.jotavesecurity.msalertas.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository repository;

    public void processaESalvaAlerta(AlertaDTO dto){
        log.info("Iniciando processamento para salvar o sensor ID {}: ", dto.sensorId());

        Alerta alerta = Alerta.fromDTO(dto);

        alerta.setStatus(AlertaStatus.PROCESSADO);
        alerta.setDataHoraProcessamento(LocalDateTime.now());

        repository.save(alerta);

        log.info("==================================================");
        log.info("Alerta persistido com sucesso com ID: {}",alerta.getId() );
        log.info("==================================================");

    }

    public void salvaFalhaDeProcessamento(AlertaDTO dto, String errorMessage){
        Alerta alerta = Alerta.fromDTO(dto);

        alerta.setStatus(AlertaStatus.FALHA);
        alerta.setDataHoraProcessamento(LocalDateTime.now());

        repository.save(alerta);

        log.info("==================================================");
        log.error("Erro ao processar o alerta com ID: {}, Motivo: {}",alerta.getId(), errorMessage);
        log.info("==================================================");

    }
}
