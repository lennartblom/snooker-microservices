package de.blom.microservices.snooker.gameinformationservice.clients;

import de.blom.microservices.snooker.gameinformationservice.models.Break;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("HighestBreakService")
public interface HighestBreakService {
    @RequestMapping(method = RequestMethod.GET, value = "/breaks/highest")
    Break getHighestBreak();

    @RequestMapping(method = RequestMethod.GET, value = "/breaks")
    List<Break> getBreaks();
}
