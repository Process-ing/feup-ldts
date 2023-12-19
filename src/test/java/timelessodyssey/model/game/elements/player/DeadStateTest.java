package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DeadStateTest {
    private Player player;
    private DeadState deadState;

    @BeforeEach
    void setup() {
        Scene mockedScene = mock(Scene.class);
        player = new Player(0, 0, mockedScene);
        deadState = new DeadState(player, 50);
        player.setState(deadState);
        player.setFacingRight(true);
        player.setVelocity(new Vector(0, 0));
    }

    @Test
    void jump() {
        Vector result = deadState.jump();

        assertEquals(0, result.x());
        assertEquals(0, result.y());
    }

    @Test
    void dash() {
        Vector result = deadState.dash();

        assertEquals(0, result.x());
        assertEquals(0, result.y());
        assertTrue(player.isFacingRight());
    }

    @Test
    void updateVelocity() {
        Vector result = deadState.updateVelocity(player.getVelocity());

        assertEquals(0, result.x());
        assertEquals(0, result.y());
        assertEquals(deadState.getDuration(), 49);

    }

    @Test
    void getNextStateAfterDuration() {
        deadState = new DeadState(player, 0);

        PlayerState nextState = deadState.getNextState();

        assertNull(nextState);
    }

    @Test
    void getNextStateStay() {
        PlayerState nextState = deadState.getNextState();

        assertSame(deadState, nextState);
    }
}