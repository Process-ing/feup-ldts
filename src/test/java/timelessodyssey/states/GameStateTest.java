package timelessodyssey.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.scene.Scene;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameStateTest {
    Scene model;
    Game game;
    GUI gui;
    Controller<Scene> stateController;
    ScreenViewer<Scene> stateScreenViewer;
    GameState state;

    @SuppressWarnings("unchecked")
    private void mockControllerAndViewer() {
        this.stateController = Mockito.mock(Controller.class);
        this.stateScreenViewer = Mockito.mock(ScreenViewer.class);
    }

    @BeforeEach
    public void setup() {
        this.model = Mockito.mock(Scene.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(GUI.class);
        mockControllerAndViewer();
    }

    @Test
    public void step() throws IOException, URISyntaxException, FontFormatException {
        double time = 0;
        Mockito.when(gui.getNextAction()).thenReturn(GUI.Action.NONE);
        this.state = new GameState(model){
            @Override
            protected ScreenViewer<Scene> createScreenViewer() {
                return stateScreenViewer;
            }
            @Override
            protected Controller<Scene> createController() {
                return stateController;
            }

        };


        state.step(game, gui, time);

        Mockito.verify(gui, Mockito.times(1)).getNextAction();
        Mockito.verify(gui, Mockito.times(0)).clearAction();
        Mockito.verify(stateController, Mockito.times(1))
                .step(game, GUI.Action.NONE, time);
        Mockito.verify(stateScreenViewer, Mockito.times(1)).draw(gui);
    }
}
