package br.com.jotavesecurity.msalertas.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // 1. Nome da Fila
    @Value("${app.rabbitmq.queue}")
    public String queueName;

    // 2. Nome da Exchange
    @Value("${app.rabbitmq.exchange}")
    public String exchangeName;

    // 3. Nome da Routing Key (Chave de Roteamento)
    @Value("${app.rabbitmq.routing-key}")
    public String routingKey;

    // 4. Criação da Fila (Queue)
    @Bean
    public Queue queue(){
        return new Queue(queueName, true);
    }

    // 5. Criação da Exchange
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    // 6. Criação do Binding (a ligação entre a Exchange e a Fila)
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue()) // Liga a fila que criamos
                .to(exchange()) // com a exchange que criamos
                .with(routingKey); // usando esta chave de roteamento
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
