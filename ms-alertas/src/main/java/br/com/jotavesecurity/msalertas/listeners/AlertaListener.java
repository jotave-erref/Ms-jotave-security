package br.com.jotavesecurity.msalertas.listeners;

import br.com.jotavesecurity.msalertas.DTO.AlertaDTO;
import br.com.jotavesecurity.msalertas.service.AlertaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AlertaListener {

    private final AlertaService service;

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receberAlerta(@Payload AlertaDTO alertaDTO){
        log.info("Mensagem recebida do RabbitMQ: {}", alertaDTO);

        try{
            service.processaESalvaAlerta(alertaDTO);
        }catch (Exception e){
            service.salvaFalhaDeProcessamento(alertaDTO, e.getMessage());
        }
    }
}
