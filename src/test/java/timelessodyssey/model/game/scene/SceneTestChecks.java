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

public class SceneTestChecks {

    Game game;
    Scene scene;
    Player player;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.scene = new Scene(3, 3, 0);
        this.player = new Player(0,0, null);
        this.scene.setPlayer(player);
    }

    @Test
    public void CheckGravityAndFriction() {
        assertEquals(0.25, scene.getGravity());
        assertEquals(0.75, scene.getFriction());
    }

    @Nested
    class SceneTestDeaths {

        @BeforeEach
        public void setup(){
            Spike[][] spikeSet = {{null, null, null},
                    {null, new Spike(8, 8, '^'), null},
                    {null, null, null}};
            scene.setSpikes(spikeSet);
        }
        @Test
        public void CheckPlayerIsDying() {



        }
    }
}
