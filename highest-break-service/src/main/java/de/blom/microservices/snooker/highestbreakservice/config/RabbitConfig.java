package de.blom.microservices.snooker.highestbreakservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.break_finished.exchange.name}")
    private String finishedBreakExchangeName;

    @Value("${rabbitmq.break_finished.queue.name}")
    private String finishedBreaksQueueName;

    @Bean
    public Queue finishedBreaksQueue(){
        return new Queue(this.finishedBreaksQueueName);
    }

    @Bean
    public Binding binding(){
        return new Binding(this.finishedBreaksQueueName, Binding.DestinationType.QUEUE, this.finishedBreakExchangeName, "", Collections.emptyMap());
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
