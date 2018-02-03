package de.blom.microservices.snooker.breakservice.client;

import de.blom.microservices.snooker.breakservice.domain.models.Shot;
import de.blom.microservices.snooker.breakservice.domain.models.ShotResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ShotServiceClient", url="http://localhost:8090")
public interface ShotServiceClient{
    @RequestMapping(method = RequestMethod.POST, value = "/shot")
    ShotResult sendShot(@RequestBody Shot shot);
}
