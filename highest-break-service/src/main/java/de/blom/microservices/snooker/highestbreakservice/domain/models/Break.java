package de.blom.microservices.snooker.highestbreakservice.domain.models;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Break{
    private int playerId;
    private int breakValue;
    private List<BallType> pottedBalls;
}
