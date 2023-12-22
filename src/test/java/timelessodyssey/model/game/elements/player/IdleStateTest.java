package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdleStateTest {
    private Player player;
    private IdleState idleState;
    private Scene mockedScene;

    @BeforeEach
    void setup() {
        mockedScene = mock(Scene.class);
        player = new Player(0, 0, mockedScene);
        idleState = new IdleState(player);
        player.setState(idleState);
        player.setVelocity(new Vector(0, 0));
        when(mockedScene.getFriction()).thenReturn(0.75);
    }

    @Test
    void jump() {
        Vector result = idleState.jump();

        assertEquals(0.0, result.x());
        assertEquals(-3.6, result.y());
    }

    @Test
    void dashRight() {
        player.setFacingRight(true);
        Vector result = idleState.dash();

        assertEquals(5.0, result.x());
        assertEquals(0.0, result.y());
        assertTrue(player.isFacingRight());
    }

    @Test
    void dashLeft() {
        player.setFacingRight(false);
        Vector result = idleState.dash();

        assertEquals(-5.0, result.x());
        assertEquals(0.0, result.y());
        assertFalse(player.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = idleState.updateVelocity(player.getVelocity());

        assertEquals(0.0, result.x());
        assertEquals(0.0, result.y());

    }

    @Test
    void getNextStateDead() {
        when(mockedScene.isPlayerDying()).thenReturn(true);

        PlayerState nextState = idleState.getNextState();

        assertInstanceOf(DeadState.class, nextState);
        verify(mockedScene, times(1)).setDeathParticles(anyList());
        verify(mockedScene, times(0)).setDeathParticles(Collections.EMPTY_LIST);
    }

    @Test
    void getNextStateDashing() {
        player.setVelocity(new Vector(10, player.getVelocity().y()));

        PlayerState nextState = idleState.getNextState();

        assertInstanceOf(DashingState.class, nextState);
    }

    @Test
    void getNextStateJumping() {
        when(player.isOnGround()).thenReturn(false);
        player.setVelocity(new Vector(player.getVelocity().x(), -10));

        PlayerState nextState = idleState.getNextState();

        assertInstanceOf(JumpingState.class, nextState);
    }

    @Test
    void getNextStateFalling() {
        when(player.isOnGround()).thenReturn(false);
        player.setVelocity(new Vector(player.getVelocity().x(), 10));

        PlayerState nextState = idleState.getNextState();

        assertInstanceOf(FallingState.class, nextState);
    }

    @Test
    void getNextStateWalking() {
        when(player.isOnGround()).thenReturn(true);
        player.setVelocity(new Vector(1, 0));

        PlayerState nextState = idleState.getNextState();

        assertInstanceOf(WalkingState.class, nextState);
    }

    @Test
    void getNextStateStay() {
        when(player.isOnGround()).thenReturn(true);
        PlayerState nextState = idleState.getNextState();

        assertSame(idleState, nextState);
    }
}