package de.blom.microservices.snooker.shotservice.domain.events;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoulOccurredEvent implements Serializable {
    private int foulPoints;
    private int playerId;
}
