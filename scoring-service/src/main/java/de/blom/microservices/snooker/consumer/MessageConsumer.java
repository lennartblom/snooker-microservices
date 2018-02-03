package de.blom.microservices.snooker.consumer;

import de.blom.microservices.snooker.events.FoulOccurredEvent;
import de.blom.microservices.snooker.events.PottedBallEvent;
import de.blom.microservices.snooker.services.ScoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    private ScoringService scoringService;

    @Autowired
    public MessageConsumer(ScoringService scoringService){
        this.scoringService = scoringService;
    }

    @RabbitListener(queues = "snooker.potted-balls.scoringservice")
    public void consumePottedBall(PottedBallEvent pottedBallEvent) {
        log.info("ScoringService::consumePottedBall");
        this.scoringService.handleIncomingPottedBall(pottedBallEvent);
    }

    @RabbitListener(queues = "snooker.occurred-fouls.scoringservice")
    public void consumeOccurredFoul(FoulOccurredEvent foulOccurredEvent){
        this.scoringService.handleIncomingOccurredFoul(foulOccurredEvent);
    }
}
