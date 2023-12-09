package timelessodyssey.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuStateTest {

    MainMenu model;
    Game game;
    GUI gui;
    Controller<MainMenu> stateController;
    ScreenViewer<MainMenu> stateScreenViewer;
    MainMenuState state;

    @SuppressWarnings("unchecked")
    private void mockControllerAndViewer() {
        this.stateController = Mockito.mock(Controller.class);
        this.stateScreenViewer = Mockito.mock(ScreenViewer.class);
    }

    @BeforeEach
    public void setup() throws IOException {
        this.model = Mockito.mock(MainMenu.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(GUI.class);
        mockControllerAndViewer();
    }

    @Test
    public void MainMenuStep() throws IOException, URISyntaxException, FontFormatException {
        long time = 0;
        Mockito.when(gui.getNextAction()).thenReturn(GUI.Action.NONE);
        this.state = new MainMenuState(model){
            @Override
            protected ScreenViewer<MainMenu> createScreenViewer() {
                return stateScreenViewer;
            }
            @Override
            protected Controller<MainMenu> createController() {
                return stateController;
            }

        };


        state.step(game, gui, time);

        Mockito.verify(gui, Mockito.times(1)).getNextAction();
        Mockito.verify(gui, Mockito.times(1)).clearAction();
        Mockito.verify(stateController, Mockito.times(1))
                .step(game, GUI.Action.NONE, time);
        Mockito.verify(stateScreenViewer, Mockito.times(1)).draw(gui);
    }
}
