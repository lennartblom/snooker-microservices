package de.blom.microservices.snooker.pottingservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class RabbitConfig {


    @Value("${rabbitmq.potted_ball.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.potting_service.queue.name}")
    private String queueName;

    @Bean
    public Queue messageQueue(){
        return new Queue(this.queueName);
    }

    @Bean
    public Binding binding(){
        return new Binding(this.queueName, Binding.DestinationType.QUEUE, this.exchangeName, "", Collections.emptyMap());
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
