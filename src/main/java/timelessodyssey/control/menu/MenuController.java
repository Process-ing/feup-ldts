package timelessodyssey.control.menu;

import timelessodyssey.model.menu.Menu;
import timelessodyssey.states.State;
import timelessodyssey.states.GameState;

import java.io.IOException;

public class MenuController extends Controller<Menu>{

    public MenuController(Menu menu) { super.menu; }

    @Override
    public abstract void step(Game game, GUI.ACTION action, long time) throws IOException{
        switch (action) {
            case UP:
                this.getModel().moveUp();
                break;

            case DOWN:
                this.getModel().moveDown();
                break;

            case SELECT:
                if (this.getMode().isExit()) {
                    game.setState(null);
                }
                else {
                    State nextState = new GameState(new LoaderMapBuilder(1).createArena());
                    game.setState(nextState);
                }
        }

    }

}
