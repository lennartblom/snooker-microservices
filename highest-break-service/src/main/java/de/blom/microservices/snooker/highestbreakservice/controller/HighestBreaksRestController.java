package de.blom.microservices.snooker.highestbreakservice.controller;

import de.blom.microservices.snooker.BallType;
import de.blom.microservices.snooker.highestbreakservice.domain.models.Break;
import de.blom.microservices.snooker.highestbreakservice.services.HighestBreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HighestBreaksRestController {

    private HighestBreakService highestBreakService;

    @Autowired
    public HighestBreaksRestController(HighestBreakService highestBreakService){
        this.highestBreakService = highestBreakService;
    }

    @GetMapping("/breaks")
    public List<Break> getAllBreaks(){
        return this.highestBreakService.getBreaks();
    }

    @GetMapping("/breaks/highest")
    public Break getHighestBreak(){
        return this.highestBreakService.getHighestBreak();
    }

    @GetMapping("/breaks/top5")
    public List<Break> getTop5Breaks(){
        return this.highestBreakService.getTop5Breaks();
    }
}
