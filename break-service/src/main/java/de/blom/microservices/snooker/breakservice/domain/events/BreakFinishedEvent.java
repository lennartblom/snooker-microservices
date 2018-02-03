package de.blom.microservices.snooker.breakservice.domain.events;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BreakFinishedEvent {
    private int playerId;
    private int breakValue;
    private List<BallType> pottedBalls;
}
