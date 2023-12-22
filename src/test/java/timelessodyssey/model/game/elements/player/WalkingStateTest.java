package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WalkingStateTest {
    private Player player;
    private Scene mockedScene;

    @BeforeEach
    void setup() {
        mockedScene = mock(Scene.class);
        player = new Player(0, 0, mockedScene);
        WalkingState walkingState = new WalkingState(player);
        player.setState(walkingState);
        when(mockedScene.getFriction()).thenReturn(0.75);
    }

    @Test
    void jump() {
        player.setVelocity(new Vector(1, 0));
        Vector result = player.jump();

        assertEquals(0.75, result.x());
        assertEquals(-3.6, result.y());
    }

    @Test
    void dashRight() {
        player.setFacingRight(true);
        player.setVelocity(new Vector(1, 0));
        Vector result = player.dash();

        assertEquals(5.0, result.x());
        assertEquals(0.0, result.y());
        assertTrue(player.isFacingRight());
    }

    @Test
    void dashLeft() {
        player.setFacingRight(false);
        player.setVelocity(new Vector(-1, 0));
        Vector result = player.dash();

        assertEquals(-5.0, result.x());
        assertEquals(0.0, result.y());
        assertFalse(player.isFacingRight());
    }

    @Test
    void updateVelocity() {
        player.setVelocity(new Vector(1, 0));
        Vector result = player.updateVelocity();

        assertEquals(0.75, result.x());
        assertEquals(0.0, result.y());
    }

    @Test
    void getNextStateDead() {
        player.setVelocity(new Vector(1, 0));
        when(mockedScene.isPlayerDying()).thenReturn(true);

        PlayerState nextState = player.getNextState();

        assertInstanceOf(DeadState.class, nextState);
        verify(mockedScene, times(1)).setDeathParticles(anyList());
        verify(mockedScene, times(0)).setDeathParticles(Collections.EMPTY_LIST);
    }

    @Test
    void getNextStateDashing() {
        player.setVelocity(new Vector(1, 0));
        player.setVelocity(new Vector(10, player.getVelocity().y()));

        PlayerState nextState = player.getNextState();

        assertInstanceOf(DashingState.class, nextState);
    }

    @Test
    void getNextStateJumping() {
        when(player.isOnGround()).thenReturn(false);
        player.setVelocity(new Vector(1, -10));

        PlayerState nextState = player.getNextState();

        assertInstanceOf(JumpingState.class, nextState);
    }

    @Test
    void getNextStateFalling() {
        player.setVelocity(new Vector(1, 10));

        PlayerState nextState = player.getNextState();

        assertInstanceOf(FallingState.class, nextState);
    }

    @Test
    void getNextStateRunning() {
        when(player.isOnGround()).thenReturn(true);
        player.setVelocity(new Vector(1.8, 0));

        PlayerState nextState = player.getNextState();

        assertInstanceOf(RunningState.class, nextState);
    }

    @Test
    void getNextStateIdle() {
        when(player.isOnGround()).thenReturn(true);
        player.setVelocity(new Vector(0.5, 0));

        PlayerState nextState = player.getNextState();

        assertInstanceOf(IdleState.class, nextState);
    }

    @Test
    void getNextStateStay() {
        when(player.isOnGround()).thenReturn(true);
        player.setVelocity(new Vector(1, 0));
        PlayerState nextState = player.getNextState();

        assertInstanceOf(WalkingState.class, nextState);
    }

    @Test
    void movePlayerLeft() {
        player.setVelocity(new Vector(-1, 0));
        when(mockedScene.collidesLeft(any(), any())).thenReturn(false);
        Vector result = player.moveLeft();

        assertEquals(-1.3125, result.x());
        assertEquals(0, result.y());

        when(mockedScene.collidesLeft(any(), any())).thenReturn(true);
        result = player.moveLeft();

        assertEquals(0, result.x());
        assertEquals(0, result.y());
    }

    @Test
    void movePlayerRight() {
        player.setVelocity(new Vector(1, 0));
        when(mockedScene.collidesRight(any(), any())).thenReturn(false);
        Vector result = player.moveRight();

        assertEquals(1.3125, result.x());
        assertEquals(0, result.y());

        when(mockedScene.collidesRight(any(), any())).thenReturn(true);
        result = player.moveRight();

        assertEquals(0, result.x());
        assertEquals(0, result.y());
    }
}