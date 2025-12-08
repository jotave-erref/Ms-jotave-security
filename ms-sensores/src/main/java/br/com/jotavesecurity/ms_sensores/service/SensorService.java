package br.com.jotavesecurity.ms_sensores.service;

import br.com.jotavesecurity.ms_sensores.amqp.AlertaProducer;
import br.com.jotavesecurity.ms_sensores.clients.CondominiosClient;
import br.com.jotavesecurity.ms_sensores.dtos.AlertaDTO;
import br.com.jotavesecurity.ms_sensores.dtos.ApartamentoResponseDTO;
import br.com.jotavesecurity.ms_sensores.dtos.SensorEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensorService {

    private final CondominiosClient client;

    private final AlertaProducer alertaProducer;


    public void processarEvento(SensorEventDTO evento){
        log.info("Processando evento para o sensor ID: {}", evento.sensorId());
        log.debug("Detalhes completos do evento: {}", evento);

        try {

            // 1. Extrai o ID do apartamento a partir do sensorId.
            String numeroApto = evento.sensorId().replaceAll("\\D+", "");
            Long aptoId = Long.parseLong(numeroApto);

            // 2. Usa o Feign Client para buscar os dados do apartamento no ms-condominos.
            log.info("Buscando dados do apartamento com ID: {}", aptoId);   
            ApartamentoResponseDTO apto = client.buscarPorId(aptoId);
            log.info("Dados recebidos do ms-condominios: {}", apto);

            // Regra de negócio caso o alarme for acionado entre as 00 hrs e 6hrs
            boolean isAlerta = "ABERTO".equalsIgnoreCase(evento.status()) &&
                    (evento.timestamp().getHour() >= 0 && evento.timestamp().getHour() <= 6);

            if(isAlerta){
                log.warn("ALERTA DETECTADO. Publicando mensagem na fila...");

                // Cria o DTO com os dados para enviar
                AlertaDTO alertaParaEnviar = new AlertaDTO(evento.sensorId(), apto.numero(), apto.bloco(),
                        evento.timestamp());

                // Chama o producer para enviar a mensagem
                alertaProducer.publicarMensagemDeAlerta(alertaParaEnviar);

                log.info("Mensagem de alerta publicada com sucesso!");
            }

        } catch (Exception e) {
            log.error("Falha ao processar o evento do sensor. Causa: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao processar timestamp do evento", e);
        }

    }
}
