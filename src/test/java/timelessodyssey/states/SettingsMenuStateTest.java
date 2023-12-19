package timelessodyssey.states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.menu.SettingsMenu;
import timelessodyssey.view.SpriteLoader;
import timelessodyssey.view.ViewerProvider;
import timelessodyssey.view.screens.ScreenViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class SettingsMenuStateTest {
    private SettingsMenu model;
    private SpriteLoader spriteLoader;
    private Game game;
    private ResizableGUI gui;
    private Controller<SettingsMenu> stateController;
    private ScreenViewer<SettingsMenu> stateScreenViewer;
    private SettingsMenuState state;

    @SuppressWarnings("unchecked")
    private void mockControllerAndViewer() {
        this.stateController = Mockito.mock(Controller.class);
        this.stateScreenViewer = Mockito.mock(ScreenViewer.class);
    }

    @BeforeEach
    public void setup() {
        this.model = Mockito.mock(SettingsMenu.class);
        this.spriteLoader = Mockito.mock(SpriteLoader.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(ResizableGUI.class);
        mockControllerAndViewer();
    }

    @Test
    public void settingsMenuStep() throws IOException, URISyntaxException, FontFormatException {
        long frameCount = 0;
        Mockito.when(gui.getNextAction()).thenReturn(GUI.Action.NONE);
        this.state = new SettingsMenuState(model, spriteLoader){
            @Override
            protected ScreenViewer<SettingsMenu> createScreenViewer(ViewerProvider viewerProvider) {
                return stateScreenViewer;
            }
            @Override
            protected Controller<SettingsMenu> createController() {
                return stateController;
            }

        };


        state.step(game, gui, frameCount);

        Mockito.verify(game, Mockito.times(1))
                .setKeySpam(false);
        Mockito.verify(gui, Mockito.times(1)).getNextAction();
        Mockito.verify(stateController, Mockito.times(1))
                .step(game, GUI.Action.NONE, frameCount);
        Mockito.verify(stateScreenViewer, Mockito.times(1)).draw(gui, frameCount);
    }
}
