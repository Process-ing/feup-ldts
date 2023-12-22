package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashingStateTest {
    private Player player;
    private DashingState dashingState;
    private Scene mockedScene;

    @BeforeEach
    void setup() {
        mockedScene = mock(Scene.class);
        player = new Player(0, 0, mockedScene);
        dashingState = new DashingState(player);
        player.setState(dashingState);
        player.setFacingRight(true);
        player.setVelocity(new Vector(5.0, 2.0));
        when(mockedScene.getFriction()).thenReturn(0.75);
        when(mockedScene.getGravity()).thenReturn(0.25);
    }

    @Test
    void jump() {
        Vector result = dashingState.jump();

        assertEquals(3.75, result.x());
        assertEquals(2.25, result.y());
    }

    @Test
    void dash() {
        Vector result = dashingState.dash();

        assertEquals(3.75, result.x());
        assertEquals(2.25, result.y());
        assertTrue(player.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = dashingState.updateVelocity(player.getVelocity());

        assertEquals(3.75, result.x());
        assertEquals(2.25, result.y());

    }

    @Test
    void getNextStateDead() {
        when(mockedScene.isPlayerDying()).thenReturn(true);

        PlayerState nextState = dashingState.getNextState();

        assertInstanceOf(DeadState.class, nextState);
        verify(mockedScene, times(1)).setDeathParticles(anyList());
        verify(mockedScene, times(0)).setDeathParticles(Collections.EMPTY_LIST);
    }

    @Test
    void getNextStateAfterDash() {
        player.setVelocity(new Vector(1.8, player.getVelocity().y()));

        PlayerState nextState = dashingState.getNextState();

        assertInstanceOf(AfterDashState.class, nextState);
    }

    @Test
    void getNextStateStay() {
        PlayerState nextState = dashingState.getNextState();

        assertSame(dashingState, nextState);
    }
}