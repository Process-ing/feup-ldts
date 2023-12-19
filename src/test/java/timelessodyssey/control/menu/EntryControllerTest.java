package timelessodyssey.control.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.states.GameState;
import timelessodyssey.states.MainMenuState;
import timelessodyssey.states.SettingsMenuState;
import timelessodyssey.states.State;
import timelessodyssey.view.SpriteLoader;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class EntryControllerTest {
    private Game game;
    private Menu menu;
    private EntryController entryController;

    @BeforeEach
    public void setup(){
        this.game = Mockito.mock(Game.class);
        this.menu = Mockito.mock(Menu.class);
        doNothing().when(game).setState(isA(State.class));
        SpriteLoader spriteLoader = Mockito.mock(SpriteLoader.class);
        when(game.getSpriteLoader()).thenReturn(spriteLoader);

        this.entryController = new EntryController(menu);
    }

    @Test
    public void stepStartGame() throws IOException, URISyntaxException, FontFormatException {
        Entry e = new Entry(0,0, Entry.Type.START_GAME);
        when(menu.getCurrentEntry()).thenReturn(e);

        entryController.step(game, GUI.Action.NONE, 0);
        verify(game, Mockito.times(0))
                .setState(Mockito.any(State.class));

        entryController.step(game, GUI.Action.SELECT, 0);
        verify(game, Mockito.times(1))
                .setState(Mockito.any(GameState.class));
    }

    @Test
    public void stepSettings() throws IOException, URISyntaxException, FontFormatException {
        Entry e = new Entry(0,0, Entry.Type.SETTINGS);
        when(menu.getCurrentEntry()).thenReturn(e);

        entryController.step(game, GUI.Action.NONE, 0);
        verify(game, Mockito.times(0))
                .setState(Mockito.any(State.class));

        entryController.step(game, GUI.Action.SELECT, 0);
        verify(game, Mockito.times(1))
                .setState(Mockito.any(SettingsMenuState.class));
    }

    @Test
    public void stepExit() throws IOException, URISyntaxException, FontFormatException {
        Entry e = new Entry(0,0, Entry.Type.EXIT);
        when(menu.getCurrentEntry()).thenReturn(e);

        entryController.step(game, GUI.Action.NONE, 0);
        verify(game, Mockito.times(0))
                .setState(null);

        entryController.step(game, GUI.Action.SELECT, 0);
        verify(game, Mockito.times(1))
                .setState(null);
    }

    @Test
    public void stepMainMenu() throws IOException, URISyntaxException, FontFormatException {
        Entry e = new Entry(0,0, Entry.Type.TO_MAIN_MENU);
        when(menu.getCurrentEntry()).thenReturn(e);

        entryController.step(game, GUI.Action.NONE, 0);
        verify(game, Mockito.times(0))
                .setState(Mockito.any(State.class));

        entryController.step(game, GUI.Action.SELECT, 0);
        verify(game, Mockito.times(1))
                .setState(Mockito.any(MainMenuState.class));


    }

    @Test
    public void stepResolutionRight() throws IOException, URISyntaxException, FontFormatException {
        Entry e = new Entry(0,0, Entry.Type.RESOLUTION);
        when(menu.getCurrentEntry()).thenReturn(e);

        entryController.step(game, GUI.Action.NONE, 0);
        verify(game, Mockito.times(0))
                .getResolution();

        for (int idx = 0; idx < ResizableGUI.Resolution.values().length - 1; idx++){
            when(game.getResolution()).thenReturn(ResizableGUI.Resolution.values()[idx]);
            entryController.step(game, GUI.Action.RIGHT, 0);

            verify(game, Mockito.times(idx+1))
                    .getResolution();
            verify(game, Mockito.times(1))
                    .setResolution(ResizableGUI.Resolution.values()[idx+1]);
        }
    }

    @Test
    public void stepResolutionLeft() throws IOException, URISyntaxException, FontFormatException {
        Entry e = new Entry(0,0, Entry.Type.RESOLUTION);
        when(menu.getCurrentEntry()).thenReturn(e);

        entryController.step(game, GUI.Action.NONE, 0);
        verify(game, Mockito.times(0))
                .getResolution();

        for (int idx = 1; idx < ResizableGUI.Resolution.values().length; idx++){
            when(game.getResolution()).thenReturn(ResizableGUI.Resolution.values()[idx]);
            entryController.step(game, GUI.Action.LEFT, 0);

            verify(game, Mockito.times(idx))
                    .getResolution();
            verify(game, Mockito.times(1))
                    .setResolution(ResizableGUI.Resolution.values()[idx-1]);
        }
    }

    @Test
    public void stepResolutionSpecialCaseRight() throws IOException, URISyntaxException, FontFormatException {
        Entry e = new Entry(0,0, Entry.Type.RESOLUTION);
        when(menu.getCurrentEntry()).thenReturn(e);
        when(game.getResolution()).thenReturn(ResizableGUI.Resolution.values()[ResizableGUI.Resolution.values().length-1]);

        entryController.step(game, GUI.Action.RIGHT, 0);
        verify(game, Mockito.times(1))
                .getResolution();
        verify(game, Mockito.times(0))
                .setResolution(Mockito.any());

    }

    @Test
    public void stepResolutionSpecialCaseLeft1() throws IOException, URISyntaxException, FontFormatException {
        Entry e = new Entry(0,0, Entry.Type.RESOLUTION);
        when(menu.getCurrentEntry()).thenReturn(e);
        when(game.getResolution()).thenReturn(null);

        entryController.step(game, GUI.Action.LEFT, 0);
        verify(game, Mockito.times(1))
                .getResolution();
        verify(game, Mockito.times(0))
                .setResolution(Mockito.any());
    }

    @Test
    public void stepResolutionSpecialCaseLeft2() throws IOException, URISyntaxException, FontFormatException {
        Entry e = new Entry(0,0, Entry.Type.RESOLUTION);
        when(menu.getCurrentEntry()).thenReturn(e);
        when(game.getResolution()).thenReturn(ResizableGUI.Resolution.values()[0]);

        entryController.step(game, GUI.Action.LEFT, 0);
        verify(game, Mockito.times(1))
                .getResolution();
        verify(game, Mockito.times(1))
                .setResolution(null);
    }
}
