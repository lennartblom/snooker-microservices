package de.blom.microservices.snooker.shotservice.dtos;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShotDto {

    protected BallType ballTypeToHit;
    protected BallType firstContact;
    protected List<BallType> pottedBalls;
}
