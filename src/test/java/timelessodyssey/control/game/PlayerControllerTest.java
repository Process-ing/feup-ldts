package timelessodyssey.control.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.game.PlayerController;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.Vector;
import timelessodyssey.model.game.elements.player.Player;
import timelessodyssey.model.game.scene.Scene;

public class PlayerControllerTest {
    private Scene scene;
    private Player player;
    private Game game;

    @BeforeEach
    public void setup() {
        this.scene = Mockito.mock(Scene.class);
        this.player = Mockito.mock(Player.class);
        Mockito.when(scene.getPlayer()).thenReturn(player);
        Mockito.when(player.getScene()).thenReturn(scene);
        this.game = Mockito.mock(Game.class);
    }

    @Test
    public void step() {
        PlayerController playerController = new PlayerController(scene);

        playerController.step(game, GUI.Action.NONE, 0);
    }
}
