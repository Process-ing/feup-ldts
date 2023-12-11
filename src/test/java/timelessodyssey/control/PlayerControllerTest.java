package timelessodyssey.control;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.game.PlayerController;
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
        Mockito.when(scene.isColliding(Mockito.any(), Mockito.any())).thenReturn(true);
    }
}
