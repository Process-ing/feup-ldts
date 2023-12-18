package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

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
        player.setFacingRight(true);
        player.setVelocity(new Vector(1, -2.0));
        when(mockedScene.getFriction()).thenReturn(0.75);
        when(mockedScene.getGravity()).thenReturn(0.25);
        when(player.isOnGround()).thenReturn(false);
    }

    @Test
    void jump() {
        Vector result = jumpingState.jump();

        assertEquals(0.75, result.x());
        assertEquals(-1.75, result.y());
    }

    @Test
    void dash() {
        Vector result = jumpingState.dash();

        assertEquals(5.0, result.x());
        assertEquals(-2.0, result.y());
        assertTrue(player.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = jumpingState.updateVelocity(player.getVelocity());

        assertEquals(0.75, result.x());
        assertEquals(-1.75, result.y());

    }

    @Test
    void getNextState_Dead() {
        when(mockedScene.isDying()).thenReturn(true);

        PlayerState nextState = jumpingState.getNextState();

        assertTrue(nextState instanceof DeadState);
    }

    @Test
    void getNextState_Dashing() {
        player.setVelocity(new Vector(10, player.getVelocity().y()));

        PlayerState nextState = jumpingState.getNextState();

        assertTrue(nextState instanceof DashingState);
    }

    @Test
    void getNextState_Falling() {
        player.setVelocity(new Vector(player.getVelocity().x(), 10));

        PlayerState nextState = jumpingState.getNextState();

        assertTrue(nextState instanceof FallingState);
    }
}