package timelessodyssey.control.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.MainMenu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.eq;

public class MainMenuControllerTest {
    private Game game;
    private MainMenu mainMenu;
    private EntryController entryController;
    private MainMenuController mainMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.mainMenu = Mockito.mock(MainMenu.class);

        this.entryController = Mockito.mock(EntryController.class);

        this.mainMenuController = new MainMenuController(mainMenu, entryController);
    }

    @Test
    public void actionDownEntry() throws IOException, URISyntaxException, FontFormatException {
        for (int counter = 1; counter < 20; counter++){
            mainMenuController.step(game, GUI.Action.DOWN, 0);
            Mockito.verify(mainMenu, Mockito.times(counter)).moveDown();
        }
    }

    @Test
    public void actionUpEntry() throws IOException, URISyntaxException, FontFormatException {
        for (int counter = 1; counter < 20; counter++){
            mainMenuController.step(game, GUI.Action.UP, 0);
            Mockito.verify(mainMenu, Mockito.times(counter)).moveUp();
        }
    }

    @Test
    public void actionQuit() throws IOException, URISyntaxException, FontFormatException {
        mainMenuController.step(game, GUI.Action.QUIT, 0);
        Mockito.verify(game, Mockito.times(1)).setState(null);
    }

    @Test
    public void actionOthers() throws IOException, URISyntaxException, FontFormatException {
        mainMenuController.step(game, GUI.Action.NONE, 0);
        Mockito.verify(entryController, Mockito.times(1))
                .step(eq(game), eq(GUI.Action.NONE), Mockito.anyLong());
    }
}
