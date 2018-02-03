package de.blom.microservices.snooker.shotservice.controller;

import de.blom.microservices.snooker.shotservice.domain.ShotResult;
import de.blom.microservices.snooker.shotservice.dtos.ShotDto;
import de.blom.microservices.snooker.shotservice.services.ShotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/shot")
@Slf4j
public class ShotRestController {

    private ShotService shotService;

    @Autowired
    public ShotRestController(ShotService shotService){
        this.shotService = shotService;
    }

    @PostMapping
    public ShotResult submitNewShot(@Valid @RequestBody ShotDto shot){
        return this.shotService.handleShot(shot);
    }
}
