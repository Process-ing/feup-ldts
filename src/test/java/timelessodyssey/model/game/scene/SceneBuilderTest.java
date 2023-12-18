package timelessodyssey.model.game.scene;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Star;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.elements.player.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class SceneBuilderTest {

    Game game;
    Player player;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.player = new Player(0,0, null);
    }

    @Test
    public void LevelLoaderTest() throws IOException {
        Scene scene =  new SceneBuilder(0).createScene(player);

        Assertions.assertNotEquals(null, scene.getPlayer());
        Assertions.assertEquals(new Vector(0,7*Tile.SIZE), scene.getStartingPosition());
        Assertions.assertEquals(new Vector(20*Tile.SIZE, 1*Tile.SIZE), scene.getTransitionPositionBegin());
        Assertions.assertEquals(new Vector(21*Tile.SIZE, 5*Tile.SIZE), scene.getTransitionPositionEnd());
        Assertions.assertEquals(0, scene.getSceneCode());
        Assertions.assertNotEquals(new Tile[12][21], scene.getTiles());
        Assertions.assertNotEquals(new Spike[12][21], scene.getSpikes());
        Assertions.assertNotEquals(new Tile[12][21], scene.getGoals());
        Assertions.assertNotEquals(new Star[12][21], scene.getStars());
        Assertions.assertNotEquals(new ArrayList<>(), scene.getParticles());
    }

    @Test
    public void LevelLoaderNoPlayer() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class,
                                () -> {new SceneBuilder(100).createScene(player);},
                        "CreatePlayer was supposed to throw IllegalStateException");

        Assertions.assertEquals("Player not found within the level file!", thrown.getMessage());
    }

    @Test
    public void LevelLoaderNoLevel() {
        FileNotFoundException thrown = Assertions.assertThrows(FileNotFoundException.class,
                () -> {new SceneBuilder(-1).createScene(player);},
                "SceneBuilder was supposed to throw FileNotFoundException");

        Assertions.assertEquals("Level file not found!", thrown.getMessage());
    }
}
