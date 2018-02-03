package de.blom.microservices.snooker.pottingservice.services;

import de.blom.microservices.snooker.BallType;
import de.blom.microservices.snooker.pottingservice.domain.events.BallPottedEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class PottingServiceTest {

    private PottingService pottingService;

    @Before
    @Autowired
    public void setup(PottingService ballPottedService){
        this.pottingService = ballPottedService;
    }

   @Test
   public void noBallsPottedSumShouldBeZero(){
        Assert.assertEquals(0, this.pottingService.getOverallPottedBalls());
   }

    @Test
    public void threePottedBallsShouldSumThree(){
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.RED));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.BLACK));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.RED));
        Assert.assertEquals(3, this.pottingService.getOverallPottedBalls());
    }


    @Test
    public void threePottedRedsShouldBeThreePottedReds(){
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.RED));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.RED));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.RED));

        Assert.assertEquals(3, this.pottingService.getRedBallsPotted());
    }

    @Test
    public void allTypesOfBallsPottedShouldSumSeven(){
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.BLACK));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.PINK));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.BLUE));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.BROWN));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.GREEN));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.YELLOW));
        this.pottingService.interpretPottedBall(new BallPottedEvent(BallType.RED));

        Assert.assertEquals(7, this.pottingService.getOverallPottedBalls());

        Assert.assertEquals(1, this.pottingService.getBlackBallsPotted());
        Assert.assertEquals(1, this.pottingService.getPinkBallsPotted());
        Assert.assertEquals(1, this.pottingService.getBlueBallsPotted());
        Assert.assertEquals(1, this.pottingService.getBrownBallsPotted());
        Assert.assertEquals(1, this.pottingService.getGreenBallsPotted());
        Assert.assertEquals(1, this.pottingService.getYellowBallsPotted());
        Assert.assertEquals(1, this.pottingService.getRedBallsPotted());

    }

}