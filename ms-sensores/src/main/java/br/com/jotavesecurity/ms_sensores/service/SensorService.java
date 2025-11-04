package br.com.jotavesecurity.ms_sensores.service;

import br.com.jotavesecurity.ms_sensores.clients.CondominiosClient;
import br.com.jotavesecurity.ms_sensores.dtos.ApartamentoResponseDTO;
import br.com.jotavesecurity.ms_sensores.dtos.SensorEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class SensorService {

    private final CondominiosClient client;

    public SensorService(CondominiosClient client){
        this.client = client;
    }

    public void processarEvento(SensorEventDTO evento){
        log.info("Processando evento para o sensor ID: {}", evento.sensorId());
        log.debug("Detalhes completos do evento: {}", evento);

        try {

            // 1. Extrai o ID do apartamento a partir do sensorId.
            String numeroApto = evento.sensorId().replaceAll("\\D+", "");
            Long aptoId = Long.parseLong(numeroApto);

            // 2. Usa o Feign Client para buscar os dados do apartamento no ms-condominos.
            System.out.println("Buscando dados do apartamento com ID: " + aptoId);
            ApartamentoResponseDTO apto = client.buscarPorId(aptoId);
            System.out.println("Dados recebidos do ms-condominios: " + apto);

            // Regra de negócio caso o alarme for acionado entre as 00 hrs e 6hrs
            boolean isAlerta = "ABERTO".equalsIgnoreCase(evento.status()) &&
                    (evento.timestamp().getHour() >= 0 && evento.timestamp().getHour() <= 6);

            if(isAlerta){
                System.out.println("ALERTA GERADO!!! " +
                        "MOVIMENTO SUSPEITO NO APARTAMENTO " + apto.numero() + ", BLOCO " + apto.bloco() +
                        ", HORÁRIO " + evento.timestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            }

        } catch (Exception e) {
            log.error("Falha ao processar o evento do sensor. Causa: {}", e.getMessage(), e);
            // Aqui você poderia relançar a exceção ou tratar de outra forma
            throw new RuntimeException("Erro ao processar timestamp do evento", e);
        }

        System.out.println("Recebido o evento do sensor: " + evento);
    }
}
