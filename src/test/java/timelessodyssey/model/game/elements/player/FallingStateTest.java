package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FallingStateTest {
    private Player player;
    private FallingState fallingState;
    private Scene mockedScene;

    @BeforeEach
    void setup() {
        mockedScene = mock(Scene.class);
        player = new Player(0, 0, mockedScene);
        fallingState = new FallingState(player);
        player.setState(fallingState);
        player.setVelocity(new Vector(1, 2.0));
        when(mockedScene.getFriction()).thenReturn(0.75);
        when(mockedScene.getGravity()).thenReturn(0.25);
    }

    @Test
    void jump() {
        Vector result = fallingState.jump();

        assertEquals(0.75, result.x());
        assertEquals(2.25, result.y());
    }

    @Test
    void dashRight() {
        player.setFacingRight(true);
        Vector result = fallingState.dash();

        assertEquals(5.0, result.x());
        assertEquals(0.0, result.y());
        assertTrue(player.isFacingRight());
    }

    @Test
    void dashLeft() {
        player.setFacingRight(false);
        Vector result = fallingState.dash();

        assertEquals(-5.0, result.x());
        assertEquals(0.0, result.y());
        assertFalse(player.isFacingRight());
    }

    @Test
    void updateVelocity() {
        when(mockedScene.collidesDown(any(), any())).thenReturn(false);
        Vector result = fallingState.updateVelocity(player.getVelocity());

        assertEquals(0.75, result.x());
        assertEquals(2.25, result.y());

        when(mockedScene.collidesDown(any(), any())).thenReturn(true);
        result = fallingState.updateVelocity(player.getVelocity());

        assertEquals(0.75, result.x());
        assertEquals(0, result.y());
    }

    @Test
    void getNextStateDead() {
        when(mockedScene.isPlayerDying()).thenReturn(true);

        PlayerState nextState = fallingState.getNextState();

        assertInstanceOf(DeadState.class, nextState);
        verify(mockedScene, times(1)).setDeathParticles(anyList());
        verify(mockedScene, times(0)).setDeathParticles(Collections.EMPTY_LIST);
    }

    @Test
    void getNextStateDashing() {
        player.setVelocity(new Vector(10, player.getVelocity().y()));

        PlayerState nextState = fallingState.getNextState();

        assertInstanceOf(DashingState.class, nextState);
    }

    @Test
    void getNextStateIdle() {
        when(player.isOnGround()).thenReturn(true);
        player.setVelocity(new Vector(0.0, 0.0));

        PlayerState nextState = fallingState.getNextState();

        assertInstanceOf(IdleState.class, nextState);
    }

    @Test
    void getNextStateWalking() {
        when(player.isOnGround()).thenReturn(true);
        player.setVelocity(new Vector(1.0, 0.0));

        PlayerState nextState = fallingState.getNextState();

        assertInstanceOf(WalkingState.class, nextState);
    }

    @Test
    void getNextStateRunning() {
        when(player.isOnGround()).thenReturn(true);
        player.setVelocity(new Vector(2.0, 0.0));

        PlayerState nextState = fallingState.getNextState();

        assertInstanceOf(RunningState.class, nextState);
    }

    @Test
    void getNextStateStay() {
        PlayerState nextState = fallingState.getNextState();

        assertSame(fallingState, nextState);
    }
}