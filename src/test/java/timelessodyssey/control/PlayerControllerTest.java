package timelessodyssey.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.game.PlayerController;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Position;
import timelessodyssey.model.game.elements.Player;
import timelessodyssey.model.game.scene.Scene;

public class PlayerControllerTest {
    private Scene scene;
    private Player player;
    private Game game;
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
        this.scene = Mockito.mock(Scene.class);
        this.player = Mockito.mock(Player.class);
        Mockito.when(scene.getPlayer()).thenReturn(player);
        this.game = Mockito.mock(Game.class);
        this.playerController = new PlayerController(scene);
    }

    @Test
    public void movePlayerLeft() {
        Position pos = new Position(5, 5);
        Mockito.when(player.getPosition()).thenReturn(pos);
        long time = 0;

        playerController.step(game, GUI.Action.LEFT, time);

        Mockito.verify(player, Mockito.times(1)).setPosition(pos.getLeft());
    }

    @Test
    public void movePlayerRight() {
        Position pos = new Position(5, 5);
        Mockito.when(player.getPosition()).thenReturn(pos);
        long time = 0;

        playerController.step(game, GUI.Action.RIGHT, time);

        Mockito.verify(player, Mockito.times(1)).setPosition(pos.getRight());
    }

    @Test
    public void movePlayerUp() {
        Position pos = new Position(5, 5);
        Mockito.when(player.getPosition()).thenReturn(pos);
        long time = 0;

        playerController.step(game, GUI.Action.UP, time);

        Mockito.verify(player, Mockito.times(1)).setPosition(pos.getUp());
    }

    @Test
    public void movePlayerDown() {
        Position pos = new Position(5, 5);
        Mockito.when(player.getPosition()).thenReturn(pos);
        long time = 0;

        playerController.step(game, GUI.Action.DOWN, time);

        Mockito.verify(player, Mockito.times(1)).setPosition(pos.getDown());
    }
}
