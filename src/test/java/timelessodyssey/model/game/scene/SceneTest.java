package timelessodyssey.model.game.scene;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.elements.player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {
    Game game;
    Scene scene;
    Player player;
    Vector playerSize;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.scene = new Scene(20, 16, 0);
        this.player = new Player(0,0, null);
        this.scene.setPlayer(player);
        this.playerSize = new Vector(player.getWidth(), player.getHeight());
    }

    @Test
    public void CheckGravityAndFriction() {
        assertEquals(0.25, scene.getGravity());
        assertEquals(0.75, scene.getFriction());
    }

    @Nested
    class SceneTestTransitions {

        @Test
        public void TransitionPositionOutside() {
            Vector transitionBegin = new Vector(160,8);
            Vector transitionEnd = new Vector(168,40);
            scene.setTransitionPositionBegin(transitionBegin);
            scene.setTransitionPositionEnd(transitionEnd);
            Vector playerPosition = new Vector(0,56);
            scene.getPlayer().setPosition(playerPosition);

            assertEquals(6, player.getWidth());
            assertEquals(8, player.getHeight());
            assertEquals(transitionBegin, scene.getTransitionPositionBegin());
            assertEquals(transitionEnd, scene.getTransitionPositionEnd());
            assertFalse(scene.isAtTransitionPosition());
        }

        @Test
        public void TransitionPositionOnlyTopLeft() {
            Vector transitionBegin = new Vector(40,8);
            Vector transitionEnd = new Vector(120,40);
            scene.setTransitionPositionBegin(transitionBegin);
            scene.setTransitionPositionEnd(transitionEnd);
            Vector playerPosition = new Vector(119,39);
            scene.getPlayer().setPosition(playerPosition);

            assertEquals(transitionBegin, scene.getTransitionPositionBegin());
            assertEquals(transitionEnd, scene.getTransitionPositionEnd());
            assertTrue(scene.isAtTransitionPosition());
        }

        @Test
        public void TransitionPositionOnlyBottomRight() {
            Vector transitionBegin = new Vector(40,8);
            Vector transitionEnd = new Vector(120,40);
            scene.setTransitionPositionBegin(transitionBegin);
            scene.setTransitionPositionEnd(transitionEnd);
            Vector playerPosition = new Vector(39,7);
            scene.getPlayer().setPosition(playerPosition);

            assertEquals(transitionBegin, scene.getTransitionPositionBegin());
            assertEquals(transitionEnd, scene.getTransitionPositionEnd());
            assertTrue(scene.isAtTransitionPosition());
        }

        @Test
        public void TransitionPositionInside() {
            Vector transitionBegin = new Vector(40,8);
            Vector transitionEnd = new Vector(120,40);
            scene.setTransitionPositionBegin(transitionBegin);
            scene.setTransitionPositionEnd(transitionEnd);
            Vector playerPosition = new Vector(60,24);
            scene.getPlayer().setPosition(playerPosition);

            assertEquals(transitionBegin, scene.getTransitionPositionBegin());
            assertEquals(transitionEnd, scene.getTransitionPositionEnd());
            assertTrue(scene.isAtTransitionPosition());
        }
    }

    @Nested
    class SceneTestCollisions {
        @BeforeEach
        public void setup() {
            scene = new Scene(3, 3, 0);
            Tile[][] tileSet = {{null, null, null},
                    {null, new Tile(8,8, 'G'), null},
                    {null, null, null}};
            scene.setTiles(tileSet);
            player = new Player(0,0, null);
            scene.setPlayer(player);
        }

        @Test
        public void CheckCollisionRight() {
            for (double x = 0; x < 17; x += 0.25){
                Vector playerPosition = new Vector(x,0);
                assertFalse(scene.collidesRight(playerPosition, playerSize));
                playerPosition = new Vector(x, 16);
                assertFalse(scene.collidesRight(playerPosition, playerSize));
            }

            for (double x = 0; x < 17; x += 0.25){
                Vector playerPosition = new Vector(x,8);
                if (x < 3 || x >= 11) {
                    assertFalse(scene.collidesRight(playerPosition, playerSize));
                }
                else {
                    assertTrue(scene.collidesRight(playerPosition, playerSize));
                }
            }
        }

        @Test
        public void CheckCollisionLeft() {
            for (double x = 23.75; x >= 0; x -= 0.25){
                Vector playerPosition = new Vector(x,0);
                assertFalse(scene.collidesLeft(playerPosition, playerSize));
                playerPosition = new Vector(x, 16);
                assertFalse(scene.collidesLeft(playerPosition, playerSize));
            }

            for (double x = 23.75; x >= 0; x -= 0.25){
                Vector playerPosition = new Vector(x,8);
                if (x < 8 || x >= 16) {
                    assertFalse(scene.collidesLeft(playerPosition, playerSize));
                }
                else {
                    assertTrue(scene.collidesLeft(playerPosition, playerSize));
                }
            }
        }

        @Test
        public void CheckCollisionDown() {
            for (double y = 0; y <= 16; y += 0.25){
                Vector playerPosition = new Vector(0,y);
                assertFalse(scene.collidesRight(playerPosition, playerSize));
                playerPosition = new Vector(0, y);
                assertFalse(scene.collidesRight(playerPosition, playerSize));
            }

            for (double y = 0; y < 17; y += 0.25){
                Vector playerPosition = new Vector(8,y);
                if (y < 1 || y >= 9) {
                    assertFalse(scene.collidesDown(playerPosition, playerSize));
                }
                else {
                    assertTrue(scene.collidesDown(playerPosition, playerSize));
                }
            }
        }

        @Test
        public void CheckCollisionUp() {
            for (double y = 23.75; y >= 0; y -= 0.25){
                Vector playerPosition = new Vector(0,y);
                assertFalse(scene.collidesUp(playerPosition, playerSize));
                playerPosition = new Vector(16, y);
                assertFalse(scene.collidesUp(playerPosition, playerSize));
            }

            for (double y = 23.75; y >= 0; y -= 0.25){
                Vector playerPosition = new Vector(8,y);
                if (y < 8 || y >= 16) {
                    assertFalse(scene.collidesUp(playerPosition, playerSize));
                }
                else {
                    assertTrue(scene.collidesUp(playerPosition, playerSize));
                }
            }
        }
    }

    @Nested
    class SceneTestDeaths {

        @BeforeEach
        public void setup(){
            scene = new Scene(3, 3, 0);
            Spike[][] spikeSet = {{null, null, null},
                    {null, new Spike(8, 8, '^'), null},
                    {null, null, null}};
            scene.setSpikes(spikeSet);
            player = new Player(0,0, null);
            scene.setPlayer(player);
        }

        @Test
        public void CheckPlayerIsDying() {
            for (double x = 0; x < 3; x++){
                for (double y = 0; y < 17; y++){
                    player.setPosition(new Vector(x,y));
                    assertFalse(scene.isDying());
                    player.setPosition(new Vector(24-8+x, y));
                    assertFalse(scene.isDying());
                }
            }
            for (double x = 0; x < 17; x++){
                player.setPosition(new Vector(x,0));
                assertFalse(scene.isDying());
                player.setPosition(new Vector(x, 16));
                assertFalse(scene.isDying());
            }
            for (double x = 3; x < 16; x++){
                for (double y = 5; y < 13; y++){
                    player.setPosition(new Vector(x,y));
                    assertTrue(scene.isDying());
                }
            }
        }
    }

}
