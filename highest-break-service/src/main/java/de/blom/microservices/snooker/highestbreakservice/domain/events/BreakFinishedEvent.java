package de.blom.microservices.snooker.highestbreakservice.domain.events;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BreakFinishedEvent {
    private int playerId;
    private int breakValue;
    private List<BallType> pottedBalls;
}
