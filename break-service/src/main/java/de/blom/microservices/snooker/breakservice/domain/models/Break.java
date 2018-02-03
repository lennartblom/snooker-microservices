package de.blom.microservices.snooker.breakservice.domain.models;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Break {
    private int playerId;
    private int breakValue;
    private List<BallType> pottedBalls;

    public void addPottedBalls(List<BallType> newPottedBalls){
        this.pottedBalls.addAll(newPottedBalls);
    }
}
