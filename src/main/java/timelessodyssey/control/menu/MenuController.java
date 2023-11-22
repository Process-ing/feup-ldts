package timelessodyssey.control.menu;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.states.State;
import timelessodyssey.states.GameState;

import java.io.IOException;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                this.getModel().moveUp();
                break;

            case DOWN:
                this.getModel().moveDown();
                break;

            case SELECT:
                if (this.getModel().isExit()) {
                    game.setState(null);
                }
                else {
                    State nextState = new GameState(new LoaderMapBuilder(1).createMap());
                    game.setState(nextState);
                }
        }

    }

}
