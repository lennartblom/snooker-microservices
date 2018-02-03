package de.blom.microservices.snooker.gameinformationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameInfoDto {
    private GameScoreDto score;
    private Break highestBreak;
    private List<Break> breaks;
    private PottedBallsDTO pottedBalls;

}
