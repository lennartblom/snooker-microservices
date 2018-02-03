package de.blom.microservices.snooker.gameinformationservice.clients;

import de.blom.microservices.snooker.gameinformationservice.models.GameScoreDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ScoringService")
public interface ScoringService {
    @RequestMapping(method = RequestMethod.GET, value = "/score")
    GameScoreDto getCurrentScore();
}
