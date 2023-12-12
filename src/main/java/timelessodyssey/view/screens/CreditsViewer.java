package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.credits.Credits;
import timelessodyssey.view.text.GameTextViewer;
import timelessodyssey.view.text.TextViewer;

import java.io.IOException;

public class CreditsViewer extends ScreenViewer<Credits> {

    private final TextViewer textViewer;
    public CreditsViewer(Credits model) throws IOException {
        super(model);
        this.textViewer = new GameTextViewer();
    }

    private static final TextColor backgroundColor = new TextColor.RGB(28, 28, 28);
    private static final TextColor messageColor = new TextColor.RGB(255, 255, 255);
    private static final TextColor nameColor = new TextColor.RGB(200, 200, 200);

    private static final TextColor frameColor = new TextColor.RGB(255, 255, 255);

    @Override
    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();
        drawBackgroundAndFrame(gui);
        drawMessages(gui);
        drawNames(gui);
        gui.refresh();
    }


    private void drawBackgroundAndFrame(GUI gui) {
        gui.drawRectangle(0, 0, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, gui.getHeight() - 1, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, 1, 1, gui.getHeight() - 2, frameColor);
        gui.drawRectangle(gui.getWidth() - 1, 1, 1, gui.getHeight() - 2, frameColor);
        gui.drawRectangle(1, 1, gui.getWidth() - 2, gui.getHeight() - 2, backgroundColor);
    }

    private void drawMessages(GUI gui) {
        int yAlignment = 20;
        int spacing = 20;
        for (int idx = 0; idx < getModel().getMessages().length ; idx++){
            String message = getModel().getMessages()[idx];
            // This method should access the charWidth and Spacing (can't be done through the interface)
            int messageLength = message.length() * 3 + message.length() - 1;
            textViewer.draw(message,
                    (gui.getWidth() / 2) - (messageLength / 2),
                    yAlignment + spacing * idx,
                    messageColor, gui);
        }

    }

    private void drawNames(GUI gui) {
        int xAlignment = 100;
        int yAlignment = 60;
        int spacing = 10;
        for (int idx = 0; idx < getModel().getNames().length ; idx++){
            textViewer.draw(getModel().getNames()[idx],
                    xAlignment,
                    yAlignment + spacing * idx,
                    nameColor, gui);
        }
    }
}

