package de.blom.microservices.snooker.services;


import de.blom.microservices.snooker.events.FoulOccurredEvent;
import de.blom.microservices.snooker.events.PottedBallEvent;
import de.blom.microservices.snooker.models.GameScoreDto;
import org.springframework.stereotype.Component;

@Component
public class ScoringService {
    private int scorePlayerOne;
    private int scorePlayerTwo;

    public ScoringService(){
        this.scorePlayerOne = 0;
        this.scorePlayerTwo = 0;
    }

    public void handleIncomingPottedBall(PottedBallEvent pottedBallEvent){
        if(pottedBallEvent.getPlayerId() == 1){
            this.scorePlayerOne += pottedBallEvent.getBallValue();
        }else if(pottedBallEvent.getPlayerId() == 2){
            this.scorePlayerTwo += pottedBallEvent.getBallValue();
        }
    }

    public GameScoreDto getCurrentGameScore() {
        return new GameScoreDto(this.scorePlayerOne, this.scorePlayerTwo);
    }

    public void handleIncomingOccurredFoul(FoulOccurredEvent foulOccurredEvent) {
        if(foulOccurredEvent.getPlayerId() == 1){
            this.scorePlayerTwo += foulOccurredEvent.getFoulPoints();
        }else if(foulOccurredEvent.getPlayerId() == 2){
            this.scorePlayerOne += foulOccurredEvent.getFoulPoints();
        }
    }
}
