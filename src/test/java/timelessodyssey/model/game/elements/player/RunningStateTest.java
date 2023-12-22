package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RunningStateTest {
    private Player player;
    private RunningState runningState;
    private Scene mockedScene;

    @BeforeEach
    void setup() {
        mockedScene = mock(Scene.class);
        player = new Player(0, 0, mockedScene);
        runningState = new RunningState(player);
        player.setState(runningState);
        player.setVelocity(new Vector(1.8, 0));
        when(mockedScene.getFriction()).thenReturn(0.75);
    }

    @Test
    void jump() {
        Vector result = runningState.jump();

        assertEquals(1.35, result.x());
        assertEquals(-3.6, result.y());
    }

    @Test
    void dash() {
        player.setFacingRight(true);
        Vector result = runningState.dash();

        assertEquals(5.0, result.x());
        assertEquals(0.0, result.y());
        assertTrue(player.isFacingRight());
    }

    @Test
    void dashLeft() {
        player.setFacingRight(false);
        Vector result = runningState.dash();

        assertEquals(-5.0, result.x());
        assertEquals(0.0, result.y());
        assertFalse(player.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = runningState.updateVelocity(player.getVelocity());

        assertEquals(1.35, result.x());
        assertEquals(0.0, result.y());

    }

    @Test
    void getNextStateDead() {
        when(mockedScene.isPlayerDying()).thenReturn(true);

        PlayerState nextState = runningState.getNextState();

        assertInstanceOf(DeadState.class, nextState);
        verify(mockedScene, times(1)).setDeathParticles(anyList());
        verify(mockedScene, times(0)).setDeathParticles(Collections.EMPTY_LIST);
    }

    @Test
    void getNextStateDashing() {
        player.setVelocity(new Vector(10, player.getVelocity().y()));

        PlayerState nextState = runningState.getNextState();

        assertInstanceOf(DashingState.class, nextState);
    }

    @Test
    void getNextStateJumping() {
        when(player.isOnGround()).thenReturn(false);
        player.setVelocity(new Vector(player.getVelocity().x(), -10));

        PlayerState nextState = runningState.getNextState();

        assertInstanceOf(JumpingState.class, nextState);
    }

    @Test
    void getNextStateFalling() {
        when(player.isOnGround()).thenReturn(false);
        player.setVelocity(new Vector(player.getVelocity().x(), 10));

        PlayerState nextState = runningState.getNextState();

        assertInstanceOf(FallingState.class, nextState);
    }

    @Test
    void getNextStateWalking() {
        when(player.isOnGround()).thenReturn(true);
        player.setVelocity(new Vector(1.5, 0));

        PlayerState nextState = runningState.getNextState();

        assertInstanceOf(WalkingState.class, nextState);
    }

    @Test
    void getNextStateStay() {
        when(player.isOnGround()).thenReturn(true);
        PlayerState nextState = runningState.getNextState();

        assertInstanceOf(RunningState.class, nextState);
    }
}