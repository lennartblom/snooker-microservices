package de.blom.microservices.snooker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Foul extends Exception{
    int value;
}
