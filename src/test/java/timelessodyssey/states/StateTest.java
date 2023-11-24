package timelessodyssey.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class StateTest {
    Object model;
    Game game;
    GUI gui;
    Controller<Object> stateController;
    ScreenViewer<Object> stateScreenViewer;
    State<Object> state;

    @BeforeEach
    public void setup() {
        this.model = Mockito.mock(Object.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(GUI.class);
        this.stateController = Mockito.mock(Controller.class);
        this.stateScreenViewer = Mockito.mock(ScreenViewer.class);

        this.state = new State<>(model) {
            @Override
            protected ScreenViewer<Object> getScreenViewer() {
                return stateScreenViewer;
            }

            @Override
            protected Controller<Object> getController() {
                return stateController;
            }
        };
    }

    @Test
    public void step() throws IOException {
        long time = 0;
        Mockito.when(gui.getNextAction()).thenReturn(GUI.Action.NONE);

        state.step(game, gui, time);

        Mockito.verify(gui, Mockito.times(1)).getNextAction();
        Mockito.verify(stateController, Mockito.times(1))
                .step(game, GUI.Action.NONE, time);
        Mockito.verify(stateScreenViewer, Mockito.times(1)).draw(gui);
    }
}
