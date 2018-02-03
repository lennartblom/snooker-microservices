package de.blom.microservices.snooker.pottingservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PottedBallsDTO {
    int blacks;
    int pinks;
    int blues;
    int browns;
    int greens;
    int yellows;
    int reds;

}
