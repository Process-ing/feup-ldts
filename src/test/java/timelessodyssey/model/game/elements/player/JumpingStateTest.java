package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JumpingStateTest {
    private Player player;
    private JumpingState jumpingState;
    private Scene mockedScene;

    @BeforeEach
    void setup() {
        mockedScene = mock(Scene.class);
        player = new Player(0, 0, mockedScene);
        jumpingState = new JumpingState(player);
        player.setState(jumpingState);
        player.setVelocity(new Vector(1, -2.0));
        when(mockedScene.getFriction()).thenReturn(0.75);
        when(mockedScene.getGravity()).thenReturn(0.25);
        when(player.isOnGround()).thenReturn(false);
    }

    @Test
    void jump() {
        when(mockedScene.collidesUp(any(), any())).thenReturn(false);
        Vector result = jumpingState.jump();

        assertEquals(0.75, result.x());
        assertEquals(-1.75, result.y());

        when(mockedScene.collidesUp(any(), any())).thenReturn(true);
        result = jumpingState.jump();

        assertEquals(0.75, result.x());
        assertEquals(0, result.y());
    }

    @Test
    void dash() {
        player.setFacingRight(true);
        Vector result = jumpingState.dash();

        assertEquals(5.0, result.x());
        assertEquals(-2.0, result.y());
        assertTrue(player.isFacingRight());
    }

    @Test
    void dashLeft() {
        player.setFacingRight(false);
        Vector result = jumpingState.dash();

        assertEquals(-5.0, result.x());
        assertEquals(-2.0, result.y());
        assertFalse(player.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = jumpingState.updateVelocity(player.getVelocity());

        assertEquals(0.75, result.x());
        assertEquals(-1.75, result.y());

    }

    @Test
    void getNextStateDead() {
        when(mockedScene.isPlayerDying()).thenReturn(true);

        PlayerState nextState = jumpingState.getNextState();

        assertInstanceOf(DeadState.class, nextState);
        verify(mockedScene, times(1)).setDeathParticles(anyList());
        verify(mockedScene, times(0)).setDeathParticles(Collections.EMPTY_LIST);
    }

    @Test
    void getNextStateDashing() {
        player.setVelocity(new Vector(10, player.getVelocity().y()));

        PlayerState nextState = jumpingState.getNextState();

        assertInstanceOf(DashingState.class, nextState);
    }

    @Test
    void getNextStateFalling() {
        player.setVelocity(new Vector(player.getVelocity().x(), 10));

        PlayerState nextState = jumpingState.getNextState();

        assertInstanceOf(FallingState.class, nextState);
    }

    @Test
    void getNextStateFallingOnZeroVelocity() {
        player.setVelocity(new Vector(player.getVelocity().x(), 0));

        PlayerState nextState = jumpingState.getNextState();

        assertInstanceOf(FallingState.class, nextState);
    }

    @Test
    void getNextStateStay() {
        PlayerState nextState = jumpingState.getNextState();

        assertSame(jumpingState, nextState);
    }
}