package de.blom.microservices.snooker.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PottedBallEvent implements Serializable{
    private int ballValue;
    private int playerId;
}
