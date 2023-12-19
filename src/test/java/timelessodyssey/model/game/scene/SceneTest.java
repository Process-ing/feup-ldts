package timelessodyssey.model.game.scene;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Star;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.elements.player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {
    private Scene scene;
    private Player player;
    private Vector playerSize;

    @BeforeEach
    public void setup() {
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



        @Test
        public void notAtTransitionPositionLeft() {
            Vector transitionBegin = new Vector(40,8);
            Vector transitionEnd = new Vector(120,40);
            scene.setTransitionPositionBegin(transitionBegin);
            scene.setTransitionPositionEnd(transitionEnd);
            Vector playerPosition1 = new Vector(31,16);
            scene.getPlayer().setPosition(playerPosition1);
        }

        @Test
        public void notAtTransitionPositionRight() {
            Vector transitionBegin = new Vector(40,8);
            Vector transitionEnd = new Vector(120,40);
            scene.setTransitionPositionBegin(transitionBegin);
            scene.setTransitionPositionEnd(transitionEnd);
            Vector playerPosition = new Vector(121,16);
            scene.getPlayer().setPosition(playerPosition);

            assertEquals(transitionBegin, scene.getTransitionPositionBegin());
            assertEquals(transitionEnd, scene.getTransitionPositionEnd());
            assertFalse(scene.isAtTransitionPosition());
        }

        @Test
        public void notAtTransitionPositionUp() {
            Vector transitionBegin = new Vector(40,16);
            Vector transitionEnd = new Vector(120,40);
            scene.setTransitionPositionBegin(transitionBegin);
            scene.setTransitionPositionEnd(transitionEnd);
            Vector playerPosition = new Vector(110,0);
            scene.getPlayer().setPosition(playerPosition);

            assertEquals(transitionBegin, scene.getTransitionPositionBegin());
            assertEquals(transitionEnd, scene.getTransitionPositionEnd());
            assertFalse(scene.isAtTransitionPosition());
        }

        @Test
        public void notAtTransitionPositionDown() {
            Vector transitionBegin = new Vector(40,8);
            Vector transitionEnd = new Vector(120,40);
            scene.setTransitionPositionBegin(transitionBegin);
            scene.setTransitionPositionEnd(transitionEnd);
            Vector playerPosition = new Vector(110,41);
            scene.getPlayer().setPosition(playerPosition);

            assertEquals(transitionBegin, scene.getTransitionPositionBegin());
            assertEquals(transitionEnd, scene.getTransitionPositionEnd());
            assertFalse(scene.isAtTransitionPosition());
        }
    }

    @Nested
    class SceneTestCollisions {
        @BeforeEach
        public void setup() {
            scene = new Scene(5, 5, 0);
            Tile[][] tileSet = {{null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, new Tile(16,16, 'G'), null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}};
            scene.setTiles(tileSet);
            player = new Player(0,0, null);
            scene.setPlayer(player);
        }

        @Test
        public void checkCollisionLeft() {
            Vector playerPosition1 = new Vector(31, 20);
            Vector playerPosition2 = new Vector(24, 20);
            Vector playerPosition3 = new Vector(23, 20);
            Vector playerPosition4 = new Vector(19, 20);

            assertFalse(scene.collidesLeft(playerPosition1, playerSize));
            assertFalse(scene.collidesLeft(playerPosition2, playerSize));
            assertTrue(scene.collidesLeft(playerPosition3, playerSize));
            assertTrue(scene.collidesLeft(playerPosition4, playerSize));
        }

        @Test
        public void checkCollisionRight() {
            Vector playerPosition1 = new Vector(1, 12);
            Vector playerPosition2 = new Vector(10, 12);
            Vector playerPosition3 = new Vector(11, 12);
            Vector playerPosition4 = new Vector(15, 12);

            assertFalse(scene.collidesRight(playerPosition1, playerSize));
            assertFalse(scene.collidesRight(playerPosition2, playerSize));
            assertTrue(scene.collidesRight(playerPosition3, playerSize));
            assertTrue(scene.collidesRight(playerPosition4, playerSize));
        }

        @Test
        public void checkCollisionUp() {
            Vector playerPosition1 = new Vector(20, 31);
            Vector playerPosition2 = new Vector(20, 24);
            Vector playerPosition3 = new Vector(20, 23);
            Vector playerPosition4 = new Vector(20, 18);

            assertFalse(scene.collidesUp(playerPosition1, playerSize));
            assertFalse(scene.collidesUp(playerPosition2, playerSize));
            assertTrue(scene.collidesUp(playerPosition3, playerSize));
            assertTrue(scene.collidesUp(playerPosition4, playerSize));
        }

        @Test
        public void checkCollisionDown() {
            Vector playerPosition1 = new Vector(12, 1);
            Vector playerPosition2 = new Vector(12, 8);
            Vector playerPosition3 = new Vector(12, 9);
            Vector playerPosition4 = new Vector(12, 14);

            assertFalse(scene.collidesDown(playerPosition1, playerSize));
            assertFalse(scene.collidesDown(playerPosition2, playerSize));
            assertTrue(scene.collidesDown(playerPosition3, playerSize));
            assertTrue(scene.collidesDown(playerPosition4, playerSize));
        }
    }

    @Nested
    class SceneBoundCollisions {
        @BeforeEach
        public void setup() {
            scene = new Scene(3, 3, 0);
            player = new Player(0,0, null);
            scene.setPlayer(player);
        }

        @Test
        public void checkCollisionLeftSceneBound() {
            Vector playerPosition1 = new Vector(10, 1);
            Vector playerPosition2 = new Vector(0, 1);
            Vector playerPosition3 = new Vector(-1, 1);
            Vector playerPosition4 = new Vector(-12, 1);

            assertFalse(scene.collidesLeft(playerPosition1, playerSize));
            assertFalse(scene.collidesLeft(playerPosition2, playerSize));
            assertTrue(scene.collidesLeft(playerPosition3, playerSize));
            assertTrue(scene.collidesLeft(playerPosition4, playerSize));
        }

        @Test
        public void checkCollisionRightSceneBound() {
            Vector playerPosition1 = new Vector(10, 1);
            Vector playerPosition2 = new Vector(18, 1);
            Vector playerPosition3 = new Vector(19, 1);
            Vector playerPosition4 = new Vector(28, 1);

            assertFalse(scene.collidesRight(playerPosition1, playerSize));
            assertFalse(scene.collidesRight(playerPosition2, playerSize));
            assertTrue(scene.collidesRight(playerPosition3, playerSize));
            assertTrue(scene.collidesRight(playerPosition4, playerSize));
        }

        @Test
        public void checkCollisionUpSceneBound() {
            Vector playerPosition1 = new Vector(1, 8);
            Vector playerPosition2 = new Vector(1, 0);
            Vector playerPosition3 = new Vector(1, -1);
            Vector playerPosition4 = new Vector(1, -7);

            assertFalse(scene.collidesUp(playerPosition1, playerSize));
            assertFalse(scene.collidesUp(playerPosition2, playerSize));
            assertTrue(scene.collidesUp(playerPosition3, playerSize));
            assertTrue(scene.collidesUp(playerPosition4, playerSize));
        }

        @Test
        public void checkCollisionDownSceneBound() {
            Vector playerPosition1 = new Vector(1, 8);
            Vector playerPosition2 = new Vector(1, 16);
            Vector playerPosition3 = new Vector(1, 17);
            Vector playerPosition4 = new Vector(1, 32);

            assertFalse(scene.collidesDown(playerPosition1, playerSize));
            assertFalse(scene.collidesDown(playerPosition2, playerSize));
            assertTrue(scene.collidesDown(playerPosition3, playerSize));
            assertTrue(scene.collidesDown(playerPosition4, playerSize));
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
        public void checkPlayerIsDying() {
            for (double x = 0; x < 3; x++){
                for (double y = 0; y < 17; y++){
                    player.setPosition(new Vector(x,y));
                    assertFalse(scene.isPlayerDying());
                    player.setPosition(new Vector(24-8+x, y));
                    assertFalse(scene.isPlayerDying());
                }
            }
            for (double x = 0; x < 17; x++){
                player.setPosition(new Vector(x,0));
                assertFalse(scene.isPlayerDying());
                player.setPosition(new Vector(x, 16));
                assertFalse(scene.isPlayerDying());
            }
            for (double x = 3; x < 16; x++){
                for (double y = 5; y < 13; y++){
                    player.setPosition(new Vector(x,y));
                    assertTrue(scene.isPlayerDying());
                }
            }
        }
    }

    @Nested
    class SceneTestStars {

        @BeforeEach
        public void setup() {
            scene = new Scene(5, 5, 0);
            Star[][] stars = {{null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, new Star(16,16), null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}};
            scene.setStars(stars);
            player = new Player(0,0, null);
            scene.setPlayer(player);
        }

        @Test
        public void checkUpdateStarsLeft() {
            player.setPosition(new Vector(31, 16));
            boolean result1 = scene.updateStars();
            player.setPosition(new Vector(24, 16));
            boolean result2 = scene.updateStars();
            int stars1 = player.getStarCounter();
            player.setPosition(new Vector(23, 16));
            boolean result3 = scene.updateStars();
            int stars2 = player.getStarCounter();

            assertFalse(result1);
            assertFalse(result2);
            assertEquals(0, stars1);
            assertTrue(result3);
            assertEquals(1, stars2);
            assertNull(scene.getStars()[2][2]);
        }

        @Test
        public void checkUpdateStarsRight() {
            player.setPosition(new Vector(1, 16));
            boolean result1 = scene.updateStars();
            player.setPosition(new Vector(10, 16));
            boolean result2 = scene.updateStars();
            int stars1 = player.getStarCounter();
            player.setPosition(new Vector(11, 16));
            boolean result3 = scene.updateStars();
            int stars2 = player.getStarCounter();

            assertFalse(result1);
            assertFalse(result2);
            assertEquals(0, stars1);
            assertTrue(result3);
            assertEquals(1, stars2);
            assertNull(scene.getStars()[2][2]);
        }

        @Test
        public void checkUpdateStarsUp() {
            player.setPosition(new Vector(16, 31));
            boolean result1 = scene.updateStars();
            player.setPosition(new Vector(16, 24));
            boolean result2 = scene.updateStars();
            int stars1 = player.getStarCounter();
            player.setPosition(new Vector(16, 23));
            boolean result3 = scene.updateStars();
            int stars2 = player.getStarCounter();

            assertFalse(result1);
            assertFalse(result2);
            assertEquals(0, stars1);
            assertTrue(result3);
            assertEquals(1, stars2);
            assertNull(scene.getStars()[2][2]);
        }

        @Test
        public void checkUpdateStarsDown() {
            player.setPosition(new Vector(16, 1));
            boolean result1 = scene.updateStars();
            player.setPosition(new Vector(16, 8));
            boolean result2 = scene.updateStars();
            int stars1 = player.getStarCounter();
            player.setPosition(new Vector(16, 9));
            boolean result3 = scene.updateStars();
            int stars2 = player.getStarCounter();

            assertFalse(result1);
            assertFalse(result2);
            assertEquals(0, stars1);
            assertTrue(result3);
            assertEquals(1, stars2);
            assertNull(scene.getStars()[2][2]);
        }
    }
}
