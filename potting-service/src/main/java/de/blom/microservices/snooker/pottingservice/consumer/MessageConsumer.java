package de.blom.microservices.snooker.pottingservice.consumer;

import de.blom.microservices.snooker.pottingservice.domain.events.BallPottedEvent;
import de.blom.microservices.snooker.pottingservice.services.PottingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

    private PottingService pottingService;

    @Autowired
    public MessageConsumer(PottingService pottingService){
        this.pottingService = pottingService;
    }

    @RabbitListener(queues = "snooker.potted-balls.pottingservice")
    public void consumePottedBallMessage(BallPottedEvent ballPottedEvent){
        log.info("Message arrived: " + ballPottedEvent.getBallType());
        this.pottingService.interpretPottedBall(ballPottedEvent);
    }
}
