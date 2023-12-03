package timelessodyssey.states;

import timelessodyssey.control.Controller;
import timelessodyssey.control.menu.MenuController;
import timelessodyssey.model.menu.Menu;
import timelessodyssey.view.screens.MenuViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class MenuState extends State<Menu>{

    public MenuState(Menu model) throws IOException { super(model); }

    @Override
    protected ScreenViewer<Menu> createScreenViewer() throws IOException {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> createController() {
        return new MenuController(getModel());
    }

}
