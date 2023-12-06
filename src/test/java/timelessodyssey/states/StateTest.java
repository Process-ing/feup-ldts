package timelessodyssey.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class StateTest {
    private static class TestObject {}

    TestObject model;
    Game game;
    GUI gui;
    Controller<TestObject> stateController;
    ScreenViewer<TestObject> stateScreenViewer;
    State<TestObject> state;

    @SuppressWarnings("unchecked")
    private void mockControllerAndViewer() {
        this.stateController = Mockito.mock(Controller.class);
        this.stateScreenViewer = Mockito.mock(ScreenViewer.class);
    }

    @BeforeEach
    public void setup() throws IOException {
        this.model = Mockito.mock(TestObject.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(GUI.class);
        mockControllerAndViewer();
        this.state = new State<>(model) {
            @Override
            protected ScreenViewer<TestObject> createScreenViewer() {
                return stateScreenViewer;
            }

            @Override
            protected Controller<TestObject> createController() {
                return stateController;
            }
        };
    }

    @Test
    public void step() throws IOException, URISyntaxException, FontFormatException {
        long time = 0;
        Mockito.when(gui.getNextAction()).thenReturn(GUI.Action.NONE);

        state.step(game, gui, time);

        Mockito.verify(gui, Mockito.times(1)).getNextAction();
        Mockito.verify(stateController, Mockito.times(1))
                .step(game, GUI.Action.NONE, time);
        Mockito.verify(stateScreenViewer, Mockito.times(1)).draw(gui);
    }
}
