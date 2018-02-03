package de.blom.microservices.snooker.events;

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
