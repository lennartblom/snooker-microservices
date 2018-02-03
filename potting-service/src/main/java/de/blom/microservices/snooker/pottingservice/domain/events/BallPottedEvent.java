package de.blom.microservices.snooker.pottingservice.domain.events;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BallPottedEvent implements Serializable{
    private BallType ballType;
}
