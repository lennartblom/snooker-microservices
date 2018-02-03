package de.blom.microservices.snooker.shotservice.services;

import de.blom.microservices.snooker.BallType;
import de.blom.microservices.snooker.Foul;
import de.blom.microservices.snooker.shotservice.domain.Shot;
import de.blom.microservices.snooker.shotservice.domain.ShotResult;
import de.blom.microservices.snooker.shotservice.domain.events.BallPottedEvent;
import de.blom.microservices.snooker.shotservice.domain.events.FoulOccurredEvent;
import de.blom.microservices.snooker.shotservice.dtos.ShotDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@NoArgsConstructor
@Component
public class ShotService {

    @Value("${rabbitmq.potted_ball.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.foul.exchange.name}")
    private String foulExchangeName;

    private RabbitTemplate rabbitTemplate;
    private Exchange exchange;

    private Exchange foulExchange;

    @Autowired
    public ShotService(RabbitTemplate rabbitTemplate, Exchange exchange, Exchange foulExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.foulExchange = foulExchange;
    }

    public ShotResult handleShot(ShotDto shotDto) {
        Shot shot = new Shot(shotDto);
        try {
            shot.checkFoulRule();

            int shotValue = this.producePottedBallMessage(shot.getPottedBalls(), 1);

            return new ShotResult(shotValue, shot.getPottedBalls(), 0);
        } catch (Foul foul) {
            this.producePottedFoulBallMessages(shot.getPottedBalls(), 1);

            this.rabbitTemplate.convertAndSend(this.foulExchangeName, "", new FoulOccurredEvent(foul.getValue(), 1));
            return new ShotResult(0,null, foul.getValue());
        }
    }

    private void producePottedFoulBallMessages(List<BallType> pottedBalls, int playerId){
        pottedBalls.forEach(pottedBall -> this.rabbitTemplate.convertAndSend(this.exchangeName, "", new BallPottedEvent(pottedBall, 0, playerId)));
    }

    private int producePottedBallMessage(List<BallType> pottedBalls, int playerId) {
        if (!pottedBalls.isEmpty()) {
            int numberOfPottedBalls = pottedBalls.size();
            BallType ballType = pottedBalls.get(0);

            int ballValue = this.getBallValue(ballType);
            int shotValue = ballValue * numberOfPottedBalls;

            for (int i = 0; i < numberOfPottedBalls; i++) {
                this.rabbitTemplate.convertAndSend(this.exchangeName, "", new BallPottedEvent(ballType, ballValue, playerId));
            }
            return shotValue;
        }else{
            return 0;
        }
    }

    public static int getBallValue(BallType ballType) {
        switch (ballType) {
            case RED:
                return 1;
            case YELLOW:
                return 2;
            case GREEN:
                return 3;
            case BROWN:
                return 4;
            case BLUE:
                return 5;
            case PINK:
                return 6;
            case BLACK:
                return 7;
            default:
                return 0;
        }
    }
}
