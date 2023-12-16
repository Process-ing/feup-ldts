package timelessodyssey.model.game.scene;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {

    Game game;
    Scene scene;
    Player player;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.scene = new Scene(10, 10, 0);
        this.player = new Player(0,0, null);
        this.scene.setPlayer(player);
    }

    @Test
    public void transitionPositionOutside() {
        Vector transitionBegin = new Vector(3,3);
        Vector transitionEnd = new Vector(7,7);
        this.scene.setTransitionPositionBegin(transitionBegin);
        this.scene.setTransitionPositionEnd(transitionEnd);
        Vector playerPosition = new Vector(1,1);
        scene.getPlayer().setPosition(playerPosition);

        assertEquals(6, player.getWidth());
        assertEquals(8, player.getHeight());
        assertEquals(transitionBegin, scene.getTransitionPositionBegin());
        assertEquals(transitionEnd, scene.getTransitionPositionEnd());
        assertFalse(scene.isAtTransitionPosition());
    }

    @Test
    public void transitionPositionTopLeft() {
        Vector transitionBegin = new Vector(3,3);
        Vector transitionEnd = new Vector(7,7);
        this.scene.setTransitionPositionBegin(transitionBegin);
        this.scene.setTransitionPositionEnd(transitionEnd);
        Vector playerPosition = new Vector(7,7);
        scene.getPlayer().setPosition(playerPosition);

        assertEquals(transitionBegin, scene.getTransitionPositionBegin());
        assertEquals(transitionEnd, scene.getTransitionPositionEnd());
        assertTrue(scene.isAtTransitionPosition());
    }

    @Test
    public void transitionPositionBottomRight() {
        Vector transitionBegin = new Vector(3,3);
        Vector transitionEnd = new Vector(7,7);
        this.scene.setTransitionPositionBegin(transitionBegin);
        this.scene.setTransitionPositionEnd(transitionEnd);
        Vector playerPosition = new Vector(3,3);
        scene.getPlayer().setPosition(playerPosition);

        assertEquals(transitionBegin, scene.getTransitionPositionBegin());
        assertEquals(transitionEnd, scene.getTransitionPositionEnd());
        assertTrue(scene.isAtTransitionPosition());
    }

    @Test
    public void transitionPositionInside() {
        Vector transitionBegin = new Vector(3,3);
        Vector transitionEnd = new Vector(7,7);
        this.scene.setTransitionPositionBegin(transitionBegin);
        this.scene.setTransitionPositionEnd(transitionEnd);
        Vector playerPosition = new Vector(5,5);
        scene.getPlayer().setPosition(playerPosition);

        assertEquals(transitionBegin, scene.getTransitionPositionBegin());
        assertEquals(transitionEnd, scene.getTransitionPositionEnd());
        assertTrue(scene.isAtTransitionPosition());
    }
}
