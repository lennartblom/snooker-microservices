package de.blom.microservices.snooker.breakservice.controller;

import de.blom.microservices.snooker.breakservice.exceptions.WrongBallCalled;
import de.blom.microservices.snooker.breakservice.domain.models.BreakShot;
import de.blom.microservices.snooker.breakservice.domain.models.TableVisitResponse;
import de.blom.microservices.snooker.breakservice.services.BreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BreakRestController {

    private BreakService breakService;

    @Autowired
    public BreakRestController(BreakService breakService){
        this.breakService = breakService;
    }

    @PostMapping("/simulations/break")
    public void simulateBreak(){
        this.breakService.simulateBreak();
    }

    @PostMapping("/break/shot")
    public TableVisitResponse breakShot(@RequestBody @Valid BreakShot breakShot) throws WrongBallCalled {
        return this.breakService.handleBreakShot(breakShot);
    }

    @DeleteMapping("/break/reset")
    public void resetBreak(){
        this.breakService.resetBreakInformationToZero();
    }



    @ExceptionHandler(WrongBallCalled.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleWrongBallCalled(){
        return "You called a wrong colour. Break can not continue...";
    }



}
