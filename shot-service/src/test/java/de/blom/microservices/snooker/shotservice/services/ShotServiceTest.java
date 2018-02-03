package de.blom.microservices.snooker.shotservice.services;


import de.blom.microservices.snooker.BallType;
import de.blom.microservices.snooker.shotservice.domain.ShotResult;
import de.blom.microservices.snooker.shotservice.dtos.ShotDto;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ShotServiceTest {

    private ShotService shotService;

    @Before
    public void setup(){
        this.shotService = new ShotService();
    }
    @Test
    public void expectNoFoulShotResult(){
        ShotResult shotResult = this.shotService.handleShot(new ShotDto(BallType.RED, BallType.RED, Arrays.asList(BallType.RED)));
        Assertions.assertThat(shotResult.getFoulPoints()).isEqualTo(0);
        Assertions.assertThat(shotResult.getPottedBalls()).isEqualTo(Arrays.asList(BallType.RED));
    }
}