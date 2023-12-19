package timelessodyssey.model.game.elements.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.scene.Scene;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerTest {
    private Player player;
    private PlayerState mockedState;
    private Scene mockedScene;

    @BeforeEach
    void setup() {
        mockedState = mock(PlayerState.class);
        mockedScene = mock(Scene.class);
        player = new Player(0, 0, mockedScene);
        player.setState(mockedState);
        player.setPosition(new Vector(2, 1));
        player.setVelocity(new Vector(3, 3));
        player.setFacingRight(false);
    }

    @Test
    void updateVelocity() {
        when(mockedState.updateVelocity(any(Vector.class))).thenReturn(new Vector(3, 3));

        player.updateVelocity();

        verify(mockedState).updateVelocity(any(Vector.class));

        assertEquals(new Vector(3, 3), player.getVelocity());
    }

    @Test
    void updatePosition() {
        Vector updatedPosition = player.updatePosition();

        double expectedX = player.getPosition().x() + player.getVelocity().x();
        double expectedY = player.getPosition().y() + player.getVelocity().y();

        assertEquals(new Vector(expectedX, expectedY), updatedPosition);
    }

    @Test
    void isOnGround() {
        when(mockedScene.collidesDown(any(Vector.class), any(Vector.class))).thenReturn(true);

        boolean onGround = player.isOnGround();

        verify(mockedScene).collidesDown(any(Vector.class), any(Vector.class));

        assertTrue(onGround);
    }

    @Test
    void isNotOnGround() {
        when(mockedScene.collidesDown(any(Vector.class), any(Vector.class))).thenReturn(false);

        boolean onGround = player.isOnGround();

        verify(mockedScene).collidesDown(any(Vector.class), any(Vector.class));

        assertFalse(onGround);
    }

    @Test
    void resetValues() {
        player.resetValues();

        assertTrue(player.isFacingRight());

        assertInstanceOf(IdleState.class, player.getState());
    }

    @Test
    void increaseDeaths() {
        player.increaseDeaths();
        assertEquals(1, player.getNumberOfDeaths());
    }

    @Test
    void increaseStars() {
        player.increaseStars();
        assertEquals(1, player.getStarCounter());
    }
}
