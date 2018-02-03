package de.blom.microservices.snooker.gameinformationservice.controller;

import de.blom.microservices.snooker.gameinformationservice.clients.HighestBreakService;
import de.blom.microservices.snooker.gameinformationservice.clients.PottingService;
import de.blom.microservices.snooker.gameinformationservice.clients.ScoringService;
import de.blom.microservices.snooker.gameinformationservice.models.GameInfoDto;
import de.blom.microservices.snooker.gameinformationservice.models.PottedBallsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GameInformationRestController {

    private HighestBreakService highestBreakService;
    private PottingService pottingService;
    private ScoringService scoringService;

    @Autowired
    public GameInformationRestController(HighestBreakService highestBreakService, PottingService pottingService, ScoringService scoringService){
        this.highestBreakService = highestBreakService;
        this.pottingService = pottingService;
        this.scoringService = scoringService;
    }

    @GetMapping("game/info")
    public GameInfoDto getGameInfo(){
        return new GameInfoDto(this.scoringService.getCurrentScore(), this.highestBreakService.getHighestBreak(), this.highestBreakService.getBreaks(), this.pottingService.getPottedBallsStats());
    }
}
