package de.blom.microservices.snooker.controller;

import de.blom.microservices.snooker.models.GameScoreDto;
import de.blom.microservices.snooker.services.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class ScoringRestController {

    private ScoringService scoringService;

    @Autowired
    public ScoringRestController(ScoringService scoringService){
        this.scoringService = scoringService;
    }

    @GetMapping("/score")
    public GameScoreDto returnCurrentGameScore(){
        return this.scoringService.getCurrentGameScore();
    }

}
