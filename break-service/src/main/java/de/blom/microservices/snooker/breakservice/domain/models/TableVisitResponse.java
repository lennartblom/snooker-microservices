package de.blom.microservices.snooker.breakservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TableVisitResponse {
    private boolean playerSwitch;
    private int breakPoints;
    private int foulPoints;

}
