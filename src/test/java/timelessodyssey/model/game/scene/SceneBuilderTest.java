package timelessodyssey.model.game.scene;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.model.game.elements.Star;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.model.game.elements.player.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class SceneBuilderTest {
    private Player player;

    @BeforeEach
    public void setup() {
        this.player = new Player(0,0, null);
    }

    @Test
    public void levelLoaderConstructor() throws IOException {
        int sceneCode = 5;
        Scene scene =  new SceneBuilder(sceneCode).createScene(player);

        Assertions.assertNotEquals(null, scene.getPlayer());
        Assertions.assertEquals(new Vector(0,7*Tile.SIZE), scene.getStartingPosition());
        Assertions.assertEquals(new Vector(15*Tile.SIZE, 10*Tile.SIZE), scene.getTransitionPositionBegin());
        Assertions.assertEquals(new Vector(19*Tile.SIZE, 12*Tile.SIZE), scene.getTransitionPositionEnd());
        Assertions.assertEquals(sceneCode, scene.getSceneCode());
        Assertions.assertNotEquals(new Tile[12][21], scene.getTiles());
        Assertions.assertNotEquals(new Spike[12][21], scene.getSpikes());
        Assertions.assertNotEquals(new Tile[12][21], scene.getGoals());
        Assertions.assertNotEquals(new Star[12][21], scene.getStars());
        Assertions.assertNotEquals(new ArrayList<>(), scene.getSnow());
    }

    @Test
    public void levelLoaderNoPlayer() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class,
                                () -> new SceneBuilder(100).createScene(player),
                        "CreatePlayer was supposed to throw IllegalStateException");

        Assertions.assertEquals("Player not found within the level file!", thrown.getMessage());
    }

    @Test
    public void levelLoaderNoLevel() {
        FileNotFoundException thrown = Assertions.assertThrows(FileNotFoundException.class,
                () -> new SceneBuilder(-1).createScene(player),
                "SceneBuilder was supposed to throw FileNotFoundException");

        Assertions.assertEquals("Level file not found!", thrown.getMessage());
    }
}
