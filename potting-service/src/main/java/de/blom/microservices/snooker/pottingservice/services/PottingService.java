package de.blom.microservices.snooker.pottingservice.services;

import de.blom.microservices.snooker.pottingservice.domain.events.BallPottedEvent;
import de.blom.microservices.snooker.pottingservice.models.PottedBallsDTO;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Getter
@Component
@Slf4j
@ToString
public class PottingService {
    private int blackBallsPotted;
    private int pinkBallsPotted;
    private int blueBallsPotted;
    private int brownBallsPotted;
    private int greenBallsPotted;
    private int yellowBallsPotted;
    private int redBallsPotted;

    @Autowired
    public PottingService(RabbitTemplate rabbitTemplate) {
        this.blackBallsPotted = 0;
        this.pinkBallsPotted = 0;
        this.blueBallsPotted = 0;
        this.brownBallsPotted = 0;
        this.greenBallsPotted = 0;
        this.yellowBallsPotted = 0;
        this.redBallsPotted = 0;
    }



    private IntStream allBallsStream(){
        return IntStream.of(this.blackBallsPotted,
                this.pinkBallsPotted, this.blueBallsPotted, this.brownBallsPotted,
                this.greenBallsPotted, this.yellowBallsPotted, this.redBallsPotted);
    }

    public int getOverallPottedBalls() {
        return this.allBallsStream().sum();
    }

    public void interpretPottedBall(BallPottedEvent event) {
        log.info("Interprete potted ball " + event);
        switch (event.getBallType()) {
            case BLACK:
                this.blackBallsPotted++;
                break;
            case PINK:
                this.pinkBallsPotted++;
                break;
            case BLUE:
                this.blueBallsPotted++;
                break;
            case BROWN:
                this.brownBallsPotted++;
                break;
            case GREEN:
                this.greenBallsPotted++;
                break;
            case YELLOW:
                this.yellowBallsPotted++;
                break;
            case RED:
                this.redBallsPotted++;
                break;
            default:
                break;

        }


        log.info("Potted Ball interpretation finished. Stats are here:");
        log.info(this.getStats().toString());
    }

    public PottedBallsDTO getStats(){
        return new PottedBallsDTO(this.blackBallsPotted, this.pinkBallsPotted, this.blueBallsPotted, this.brownBallsPotted, this.greenBallsPotted, this.yellowBallsPotted, this.redBallsPotted);
    }
}
