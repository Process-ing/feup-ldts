package timelessodyssey.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.control.menu.MenuController;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.view.screens.MenuViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class MenuStateTest {

    Menu model;
    Game game;
    GUI gui;
    Controller<Menu> stateController;
    ScreenViewer<Menu> stateScreenViewer;
    MenuState state;

    @SuppressWarnings("unchecked")
    private void mockControllerAndViewer() {
        this.stateController = Mockito.mock(Controller.class);
        this.stateScreenViewer = Mockito.mock(ScreenViewer.class);
    }

    @BeforeEach
    public void setup() throws IOException {
        this.model = Mockito.mock(Menu.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(GUI.class);
        mockControllerAndViewer();
    }

    @Test
    public void step() throws IOException {
        long time = 0;
        Mockito.when(gui.getNextAction()).thenReturn(GUI.Action.NONE);
        this.state = new MenuState(model){
            @Override
            protected ScreenViewer<Menu> createScreenViewer() {
                return stateScreenViewer;
            }
            @Override
            protected Controller<Menu> createController() {
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
