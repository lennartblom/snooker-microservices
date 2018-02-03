package de.blom.microservices.snooker.gameinformationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameScoreDto {
    private int scorePlayerOne;
    private int scorePlayerTwo;
}
