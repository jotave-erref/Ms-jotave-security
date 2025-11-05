package br.com.jotavesecurity.ms_alertas.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // 1. Nome da Fila
    public static final String QUEUE_ALERTAS = "alertas.v1.gerar-alerta";

    // 2. Nome da Exchange
    public static final String EXCHANGE_ALERTAS = "alertas.v1.events";

    // 3. Nome da Routing Key (Chave de Roteamento)
    public static final String ROUTING_KEY_GERAR_ALERTA = "gerar-alerta";

    // 4. Criação da Fila (Queue)
    @Bean
    public Queue queue(){
        return new Queue(QUEUE_ALERTAS, true);
    }

    // 5. Criação da Exchange
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_ALERTAS);
    }

    // 6. Criação do Binding (a ligação entre a Exchange e a Fila)
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue()) // Liga a fila que criamos
                .to(exchange()) // com a exchange que criamos
                .with(ROUTING_KEY_GERAR_ALERTA); // usando esta chave de roteamento
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
