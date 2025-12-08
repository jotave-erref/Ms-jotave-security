package br.com.jotavesecurity.ms_sensores.amqp;

import br.com.jotavesecurity.ms_sensores.dtos.AlertaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertaProducerImpl implements AlertaProducer{

    public final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    public String exchange;

    @Value("${app.rabbitmq.routing-key}")
    public String routingKey;


    @Override
    public void publicarMensagemDeAlerta(AlertaDTO alertaDTO) {
        // método convertAnsSend()
        // 1. Converte o objeto 'alerta' para JSON (por padrão).
        // 2. Envia para a exchange especificada.
        // 3. Usa a routing key para que a exchange saiba para qual fila rotear.

        rabbitTemplate.convertAndSend(exchange, routingKey, alertaDTO);
    }
}
