package de.blom.microservices.snooker.shotservice.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.potted_ball.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.foul.exchange.name}")
    private String foulExchangeName;


    @Bean
    public Exchange exchange(){
        return new FanoutExchange(this.exchangeName);
    }

    @Bean
    public Exchange foulExchange(){
        return new FanoutExchange(this.foulExchangeName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
