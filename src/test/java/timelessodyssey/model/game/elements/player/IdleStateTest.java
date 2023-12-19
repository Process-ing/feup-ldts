package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

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
    void getNextState_Dead() {
        when(mockedScene.isDying()).thenReturn(true);

        PlayerState nextState = idleState.getNextState();

        assertTrue(nextState instanceof DeadState);
    }

    @Test
    void getNextState_Dashing() {
        player.setVelocity(new Vector(10, player.getVelocity().y()));

        PlayerState nextState = idleState.getNextState();

        assertTrue(nextState instanceof DashingState);
    }

    @Test
    void getNextState_Jumping() {
        when(player.isOnGround()).thenReturn(false);
        player.setVelocity(new Vector(player.getVelocity().x(), -10));

        PlayerState nextState = idleState.getNextState();

        assertTrue(nextState instanceof JumpingState);
    }

    @Test
    void getNextState_Falling() {
        when(player.isOnGround()).thenReturn(false);
        player.setVelocity(new Vector(player.getVelocity().x(), 10));

        PlayerState nextState = idleState.getNextState();

        assertTrue(nextState instanceof FallingState);
    }

    @Test
    void getNextState_Walking() {
        when(player.isOnGround()).thenReturn(true);
        player.setVelocity(new Vector(1, 0));

        PlayerState nextState = idleState.getNextState();

        assertTrue(nextState instanceof WalkingState);
    }

    @Test
    void getNextState_Stay() {
        when(player.isOnGround()).thenReturn(true);
        PlayerState nextState = idleState.getNextState();

        assertTrue(nextState instanceof IdleState);
    }
}