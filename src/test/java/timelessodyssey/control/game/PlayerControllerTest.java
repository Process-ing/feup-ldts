package timelessodyssey.control.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.player.IdleState;
import timelessodyssey.model.game.elements.player.Player;
import timelessodyssey.model.game.elements.player.PlayerState;
import timelessodyssey.model.game.scene.Scene;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PlayerControllerTest {
    private Scene scene;
    private Player player;
    private PlayerController playerController;
    private Game game;

    @BeforeEach
    public void setup() {
        this.scene = Mockito.mock(Scene.class);
        this.player = Mockito.mock(Player.class);
        Mockito.when(scene.getPlayer()).thenReturn(player);
        Mockito.when(player.getScene()).thenReturn(scene);
        this.game = Mockito.mock(Game.class);
        this.playerController = new PlayerController(scene);
    }

    @Nested
    class playerTestsNotDead {

        @BeforeEach
        public void setup() {
            PlayerState playerState = Mockito.mock(PlayerState.class);
            Mockito.when(player.getState()).thenReturn(playerState);
        }

        @Test
        public void step() {
            playerController.step(game, GUI.Action.NONE, 0);
            verify(player, times(1))
                    .setVelocity(Mockito.any());
            verify(player, times(1))
                    .updateVelocity();
            verify(player, times(1))
                    .setPosition(Mockito.any());
            verify(player, times(1))
                    .getNextState();
            verify(player, times(1))
                    .setState(Mockito.any());
        }

        @Test
        public void stepLeft() {
            playerController.step(game, GUI.Action.LEFT, 0);

            verify(player, times(1))
                    .moveLeft();
            verify(player, times(1))
                    .setVelocity(Mockito.any());
            verify(player, times(1))
                    .setFacingRight(false);
        }

        @Test
        public void stepRight() {
            playerController.step(game, GUI.Action.RIGHT, 0);

            verify(player, times(1))
                    .moveRight();
            verify(player, times(1))
                    .setVelocity(Mockito.any());
            verify(player, times(1))
                    .setFacingRight(true);
        }

        @Test
        public void stepJump() {
            playerController.step(game, GUI.Action.JUMP, 0);

            verify(player, times(1))
                    .jump();
            verify(player, times(1))
                    .setVelocity(Mockito.any());
        }

        @Test
        public void stepDash() {
            playerController.step(game, GUI.Action.DASH, 0);

            verify(player, times(1))
                    .dash();
            verify(player, times(1))
                    .setVelocity(Mockito.any());
        }
    }

    @Nested
    class playerTestsDead {

        @BeforeEach
        public void setup() {
            Mockito.when(player.getState()).thenReturn(null);
        }

        @Test
        public void stepDead() {
            playerController.step(game, GUI.Action.NONE, 0);

            verify(player, times(1))
                    .increaseDeaths();
            verify(player, times(2))
                    .setPosition(Mockito.any());
            verify(scene, times(1))
                    .setDeathParticles(Mockito.any());
            verify(player, times(1))
                    .setState(Mockito.any(IdleState.class));
        }
    }
}
