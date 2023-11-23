package timelessodyssey.control.menu;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Menu;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.Action action, long time) {
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
                break;
            default:
        }
    }
}
