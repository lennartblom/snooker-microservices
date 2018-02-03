package de.blom.microservices.snooker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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

    @Value("${rabbitmq.foul.exchange.name}")
    private String foulExchangeName;

    @Value("${rabbitmq.scoring-service.queue.name}")
    private String queueName;

    @Value("${rabbitmq.scoring-service.occured-fouls.queue.name}")
    private String foulQueueName;

    @Bean
    public Queue messageQueue(){
        return new Queue(this.queueName);
    }

    @Bean
    public Queue fouMessageQueue(){
        return new Queue(this.foulQueueName);
    }

    @Bean
    public Binding pottedBallsQueueBinding(){
        return new Binding(this.queueName, Binding.DestinationType.QUEUE, this.exchangeName, "", Collections.emptyMap());
    }

    @Bean
    public Binding occuredFoulsQueueBinding(){
        return new Binding(this.foulQueueName, Binding.DestinationType.QUEUE, this.foulExchangeName, "", Collections.emptyMap());
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

}
