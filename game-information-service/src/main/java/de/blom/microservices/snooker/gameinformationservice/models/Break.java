package de.blom.microservices.snooker.gameinformationservice.models;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Break {
    private int playerId;
    private int breakValue;
    private List<BallType> pottedBalls;
}
