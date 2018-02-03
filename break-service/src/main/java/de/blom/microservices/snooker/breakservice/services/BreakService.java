package de.blom.microservices.snooker.breakservice.services;

import de.blom.microservices.snooker.BallType;
import de.blom.microservices.snooker.breakservice.client.ShotServiceClient;
import de.blom.microservices.snooker.breakservice.domain.events.BreakFinishedEvent;
import de.blom.microservices.snooker.breakservice.domain.models.*;
import de.blom.microservices.snooker.breakservice.exceptions.WrongBallCalled;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.RabbitHealthIndicator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BreakService {

    private ShotServiceClient shotClient;
    private boolean isAllowedToHitColour;
    private Break currentBreak;
    private int currentPlayerId;
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.break_finished.exchange.name}")
    private String breakExchangeName;

    private List<BallType> colours;

    @Autowired
    public BreakService(ShotServiceClient shotClient, RabbitTemplate rabbitTemplate){
        this.colours = Arrays.asList(BallType.YELLOW, BallType.GREEN, BallType.BROWN, BallType.BLUE, BallType.PINK, BallType.BLACK);
        this.isAllowedToHitColour = false;
        this.currentPlayerId = 1;
        this.shotClient = shotClient;
        this.rabbitTemplate = rabbitTemplate;
        this.currentBreak = new Break(1, 0, new ArrayList<>());
    }

    public TableVisitResponse handleBreakShot(BreakShot breakShot) throws WrongBallCalled {
        if((!this.isAllowedToHitColour && breakShot.getBallTypeToHit() == BallType.RED) || (this.isAllowedToHitColour && this.colours.contains(breakShot.getBallTypeToHit()))){

            ShotResult shotResult = this.shot(breakShot);
            if(shotResult.getFoulPoints() > 0){

                this.notifiyAboutFinishedBreak();
                TableVisitResponse tableVisitResponse = new TableVisitResponse(true, this.currentBreak.getBreakValue(), shotResult.getFoulPoints());
                this.resetBreakInformationToZero();
                return tableVisitResponse;

            }else{
                if(breakShot.getBallTypeToHit() == BallType.RED){
                    this.isAllowedToHitColour = true;
                }else{
                    this.isAllowedToHitColour = false;
                }
                this.currentBreak.setBreakValue(this.currentBreak.getBreakValue() + shotResult.getShotPoints());
                this.currentBreak.addPottedBalls(shotResult.getPottedBalls());
                return new TableVisitResponse(false, this.currentBreak.getBreakValue(), 0);
            }
        }else{
            throw new WrongBallCalled();
        }

    }

    private void notifiyAboutFinishedBreak(){
        this.rabbitTemplate.convertAndSend(this.breakExchangeName, "", new BreakFinishedEvent(this.currentBreak.getPlayerId(), this.currentBreak.getBreakValue(), this.currentBreak.getPottedBalls()));
    }

    public void resetBreakInformationToZero() {
        this.currentBreak = new Break(1, 0, new ArrayList<>());
        this.isAllowedToHitColour = false;
    }

    private ShotResult shot(BreakShot breakShot){
        Shot shot = new Shot(breakShot.getBallTypeToHit(), breakShot.getFirstContact(), breakShot.getPottedBalls());
        return this.shotClient.sendShot(shot);
    }



    public void simulateBreak(){
        Shot shot = new Shot(BallType.RED, BallType.RED, Arrays.asList(BallType.RED, BallType.RED));
        this.shotClient.sendShot(shot);

        shot = new Shot(BallType.BLACK, BallType.RED, Arrays.asList(BallType.RED, BallType.RED));
        this.shotClient.sendShot(shot);
    }

}
