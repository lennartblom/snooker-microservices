package de.blom.microservices.snooker.gameinformationservice.clients;

import de.blom.microservices.snooker.gameinformationservice.models.PottedBallsDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("PottingService")
public interface PottingService {
    @RequestMapping(method = RequestMethod.GET, value = "/stats/ballsPotted")
    PottedBallsDTO getPottedBallsStats();
}
