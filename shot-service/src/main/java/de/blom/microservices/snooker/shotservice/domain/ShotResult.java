package de.blom.microservices.snooker.shotservice.domain;

import de.blom.microservices.snooker.BallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ShotResult implements Serializable{
    private int shotPoints;
    private List<BallType> pottedBalls;
    private int foulPoints;
}
