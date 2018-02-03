package de.blom.microservices.snooker.shotservice.domain;

import de.blom.microservices.snooker.BallType;
import de.blom.microservices.snooker.Foul;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ShotTest {

    @Test
    public void expectProperlyInitializedShotObject(){
        Shot shot = new Shot(BallType.RED, BallType.RED, BallType.RED, BallType.RED);

        assertThat(shot.getBallTypeToHit(), is(BallType.RED));
        assertThat(shot.getFirstContact(), is(BallType.RED));
        assertThat(shot.getPottedBalls(), is(Arrays.asList(BallType.RED, BallType.RED)));
    }

    @Test
    public void expectNoFoul() throws Foul {
        Shot shot = new Shot(BallType.RED, BallType.RED, BallType.RED);
        shot.checkFoulRule();
    }

    @Test(expected = Foul.class)
    public void expectFoulBecauseOfWrongFirstContact() throws Foul {
        Shot shot = new Shot(BallType.RED, BallType.BLACK);
        shot.checkFoulRule();
    }

    @Test(expected = Foul.class)
    public void expectFoulBecauseOfPottedRedAndBlack() throws Foul {
        Shot shot = new Shot(BallType.BLACK, BallType.BLACK, BallType.RED);
        shot.checkFoulRule();
    }

}