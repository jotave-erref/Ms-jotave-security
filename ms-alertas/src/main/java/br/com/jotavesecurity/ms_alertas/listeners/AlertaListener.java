package br.com.jotavesecurity.ms_alertas.listeners;

import br.com.jotavesecurity.ms_alertas.DTO.AlertaDTO;
import br.com.jotavesecurity.ms_alertas.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AlertaListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ALERTAS)
    public void receberAlerta(@Payload AlertaDTO alertaDTO){
        System.out.println("==========================================");
        System.out.println("ALERTA RECEBIDO NA FILA!");
        System.out.println("Sensor: " + alertaDTO.sensorId());
        System.out.println("Apartamento: " + alertaDTO.apartamento() + " Bloco: " + alertaDTO.bloco());
        System.out.println("Horário: " + alertaDTO.timestamp());
        System.out.println("==========================================");
    }
}
