package de.blom.microservices.snooker.pottingservice.controller;

import de.blom.microservices.snooker.pottingservice.models.PottedBallsDTO;
import de.blom.microservices.snooker.pottingservice.services.PottingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class PottedBallStatsController {

    private PottingService pottingService;

    @Autowired
    public PottedBallStatsController(PottingService pottingService){
        this.pottingService = pottingService;
    }
    @GetMapping("/ballsPotted")
    public PottedBallsDTO ballsPotted(){
        return this.pottingService.getStats();
    }
}
