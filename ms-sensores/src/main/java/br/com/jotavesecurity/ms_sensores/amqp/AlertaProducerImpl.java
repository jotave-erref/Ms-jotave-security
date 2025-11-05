package br.com.jotavesecurity.ms_sensores.amqp;

import br.com.jotavesecurity.ms_sensores.dtos.AlertaDTO;
import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertaProducerImpl implements AlertaProducer{

    @Autowired
    public RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE_ALERTAS = "alertas.v1.events";

    public static final String ROUTING_KEY_GERAR_ALERTA = "gerar-alerta";


    @Override
    public void publicarMensagemDeAlerta(AlertaDTO alertaDTO) {
        // método convertAnsSend()
        // 1. Converte o objeto 'alerta' para JSON (por padrão).
        // 2. Envia para a exchange especificada.
        // 3. Usa a routing key para que a exchange saiba para qual fila rotear.

        rabbitTemplate.convertAndSend(EXCHANGE_ALERTAS, ROUTING_KEY_GERAR_ALERTA, alertaDTO);
    }
}
