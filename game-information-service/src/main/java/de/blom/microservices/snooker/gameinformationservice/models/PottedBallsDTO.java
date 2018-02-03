package de.blom.microservices.snooker.gameinformationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor
public class PottedBallsDTO {
    int blacks;
    int pinks;
    int blues;
    int browns;
    int greens;
    int yellows;
    int reds;

}
