package timelessodyssey.control;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.lifecycle.BeforeTry;
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

    @BeforeTry
    public void setup() {
        this.scene = Mockito.mock(Scene.class);
        this.player = Mockito.mock(Player.class);
        Mockito.when(scene.getPlayer()).thenReturn(player);
        this.game = Mockito.mock(Game.class);
        this.playerController = new PlayerController(scene);
        Mockito.when(scene.isEmpty(Mockito.any())).thenReturn(true);
    }

    @Property
    public void movePlayerLeft(@ForAll double x, @ForAll double y) {
        Position pos = new Position(x, y);
        Mockito.when(player.getPosition()).thenReturn(pos);
        long time = 0;

        playerController.step(game, GUI.Action.LEFT, time);

        Mockito.verify(player, Mockito.times(1)).setPosition(Mockito.eq(new Position(x - 1, y)));
    }

    @Property
    public void movePlayerRight(@ForAll double x, @ForAll double y) {
        Position pos = new Position(x, y);
        Mockito.when(player.getPosition()).thenReturn(pos);
        long time = 0;

        playerController.step(game, GUI.Action.RIGHT, time);

        Mockito.verify(player, Mockito.times(1)).setPosition(Mockito.eq(new Position(x + 1, y)));
    }

    @Property
    public void movePlayerUp(@ForAll double x, @ForAll double y) {
        Position pos = new Position(x, y);
        Mockito.when(player.getPosition()).thenReturn(pos);
        long time = 0;

        playerController.step(game, GUI.Action.UP, time);

        Mockito.verify(player, Mockito.times(1)).setPosition(Mockito.eq(new Position(x, y - 1)));
    }

    @Property
    public void movePlayerDown(@ForAll double x, @ForAll double y) {
        Position pos = new Position(x, y);
        Mockito.when(player.getPosition()).thenReturn(pos);
        long time = 0;

        playerController.step(game, GUI.Action.DOWN, time);

        Mockito.verify(player, Mockito.times(1)).setPosition(Mockito.eq(new Position(x, y + 1)));
    }
}
