package de.blom.microservices.snooker.shotservice.domain;

import de.blom.microservices.snooker.BallType;
import de.blom.microservices.snooker.Foul;
import de.blom.microservices.snooker.shotservice.dtos.ShotDto;
import de.blom.microservices.snooker.shotservice.services.ShotService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class Shot extends ShotDto{
    public Shot(ShotDto shot){
        this.ballTypeToHit = shot.getBallTypeToHit();
        this.firstContact = shot.getFirstContact();
        this.pottedBalls = shot.getPottedBalls();
    }

    public Shot(BallType ballTypeToHit, BallType firstContact, List<BallType> pottedBalls) {
        super(ballTypeToHit, firstContact, pottedBalls);
    }

    public Shot(BallType ballTypeToHit, BallType firstContact, BallType ... pottedBalls) {
        super(ballTypeToHit, firstContact, Arrays.asList(pottedBalls));
    }

    public void checkFoulRule() throws Foul {
        if(this.ballTypeToHit != this.firstContact){
            int firstContactBallValue = ShotService.getBallValue(this.firstContact);
            int ballTypeToHitValue = ShotService.getBallValue(this.ballTypeToHit);
            int foulPoints = 4;
            if (firstContactBallValue > 4){
                foulPoints = firstContactBallValue;
            }
            if (ballTypeToHitValue > 4 && ballTypeToHitValue > foulPoints){
                foulPoints = ballTypeToHitValue;
            }
            throw new Foul(foulPoints);
        }
    }
}
