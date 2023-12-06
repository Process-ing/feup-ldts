package timelessodyssey.states;

import timelessodyssey.Game;
import timelessodyssey.control.Controller;
import timelessodyssey.control.menu.MenuController;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.view.screens.MenuViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class MenuState extends State<Menu>{

    public MenuState(Menu model) throws IOException { super(model); }

    @Override
    protected ScreenViewer<Menu> createScreenViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> createController() {
        return new MenuController(getModel());
    }

    @Override
    public void step(Game game, GUI gui, long time) throws IOException {
        GUI.Action action = gui.getNextAction();
        gui.clearAction();
        controller.step(game, action, time);
        screenViewer.draw(gui);
    }

}
