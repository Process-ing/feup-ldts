package timelessodyssey.control.menu;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.scene.SceneBuilder;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.states.GameState;

import java.io.IOException;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.Action action, long time) throws IOException {
        switch (action) {
            case UP:
                this.getModel().moveUp();
                break;
            case DOWN:
                this.getModel().moveDown();
                break;
            case SELECT:
                if (this.getModel().isSelectedExit()) {
                    game.setState(null);
                }
                else if (this.getModel().isSelectedStart()){
                    game.setState(new GameState(new SceneBuilder().createScene()));
                }
                break;
            default:
        }
    }
}
