package timelessodyssey.states;

import timelessodyssey.control.Controller;
import timelessodyssey.control.credits.CreditsController;
import timelessodyssey.model.credits.Credits;
import timelessodyssey.view.SpriteLoader;
import timelessodyssey.view.screens.CreditsViewer;
import timelessodyssey.view.screens.ScreenViewer;

import java.io.IOException;

public class CreditsState extends State<Credits> {
    public CreditsState(Credits model, SpriteLoader spriteLoader) throws IOException {
        super(model, spriteLoader);
    }

    @Override
    protected ScreenViewer<Credits> createScreenViewer(SpriteLoader spriteLoader) throws IOException {
        return new CreditsViewer(getModel());
    }

    @Override
    protected Controller<Credits> createController() {
        return new CreditsController(getModel());
    }
}
