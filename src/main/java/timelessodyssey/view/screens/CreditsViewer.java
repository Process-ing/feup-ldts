package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.credits.Credits;

import java.io.IOException;

public class CreditsViewer extends ScreenViewer<Credits> {
    public CreditsViewer(Credits model) {
        super(model);
    }

    private static final TextColor backgroundColor = new TextColor.RGB(28, 28, 70);
    private static final TextColor frameColor = new TextColor.RGB(255, 255, 255);

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        gui.drawRectangle(0, 0, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, gui.getHeight() - 1, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, 1, 1, gui.getHeight() - 2, frameColor);
        gui.drawRectangle(gui.getWidth() - 1, 1, 1, gui.getHeight() - 2, frameColor);
        gui.drawRectangle(1, 1, gui.getWidth() - 2, gui.getHeight() - 2, backgroundColor);
        gui.refresh();
    }
}
