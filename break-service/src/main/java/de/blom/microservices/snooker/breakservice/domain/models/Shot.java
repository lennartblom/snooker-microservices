package de.blom.microservices.snooker.breakservice.domain.models;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Shot {

    protected BallType ballTypeToHit;
    protected BallType firstContact;
    protected List<BallType> pottedBalls;
}
