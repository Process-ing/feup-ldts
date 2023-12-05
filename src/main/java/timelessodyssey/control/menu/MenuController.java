package timelessodyssey.control.menu;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Menu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class MenuController<T extends Menu> extends Controller<T> {

    public MenuController(T menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI gui, GUI.Action action, long time) throws IOException, URISyntaxException, FontFormatException {
        switch (action) {
            case UP:
                this.getModel().moveUp();
                break;
            case DOWN:
                this.getModel().moveDown();
                break;
            case QUIT:
                onQuit(game);
                break;
            default:
                this.getModel().getCurrentEntry().doAction(game, gui, action);
        }
    }

    protected abstract void onQuit(Game game) throws IOException;
}
