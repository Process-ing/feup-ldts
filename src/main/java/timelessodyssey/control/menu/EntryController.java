package timelessodyssey.control.menu;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.game.elements.player.Player;
import timelessodyssey.model.game.scene.SceneBuilder;
import timelessodyssey.model.menu.MainMenu;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.model.menu.SettingsMenu;
import timelessodyssey.states.GameState;
import timelessodyssey.states.MainMenuState;
import timelessodyssey.states.SettingsMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class EntryController extends Controller<Menu> {
    private static final ResizableGUI.Resolution[] resolutions = ResizableGUI.Resolution.values();

    public EntryController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.Action action, long frameCount) throws IOException, URISyntaxException, FontFormatException {
        switch (getModel().getCurrentEntry().getType()) {
            case START_GAME:
                if (action == GUI.Action.SELECT)
                    game.setState(new GameState(
                        new SceneBuilder(0).createScene(new Player(0,0, null)),
                        game.getSpriteLoader()
                    ));
                break;
            case SETTINGS:
                if (action == GUI.Action.SELECT)
                    game.setState(new SettingsMenuState(new SettingsMenu(), game.getSpriteLoader()));
                break;
            case EXIT:
                if (action == GUI.Action.SELECT)
                    game.setState(null);
                break;
            case RESOLUTION:
                if (action == GUI.Action.RIGHT) {
                    int index = getResolutionIndex(game.getResolution());
                    if (index < resolutions.length - 1) {
                        ResizableGUI.Resolution newResolution = resolutions[index + 1];
                        game.setResolution(newResolution);
                    }
                } else if (action == GUI.Action.LEFT) {
                    int index = getResolutionIndex(game.getResolution());
                    if (index > -1) {
                        ResizableGUI.Resolution newResolution = index != 0 ? resolutions[index - 1] : null;
                        game.setResolution(newResolution);
                    }
                }
                break;
            case TO_MAIN_MENU:
                if (action == GUI.Action.SELECT)
                    game.setState(new MainMenuState(new MainMenu(), game.getSpriteLoader()));
        }
    }

    private Integer getResolutionIndex(ResizableGUI.Resolution resolution) {
        for (int i = 0; i < resolutions.length; i++) {
            if (resolutions[i] == resolution)
                return i;
        }
        return -1;
    }
}
