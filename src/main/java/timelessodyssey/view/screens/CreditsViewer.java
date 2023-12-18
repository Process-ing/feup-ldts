package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.credits.Credits;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.text.GameTextViewer;
import timelessodyssey.view.text.TextViewer;

import java.io.IOException;

import static timelessodyssey.view.text.GameTextViewer.*;

public class CreditsViewer extends ScreenViewer<Credits> {

    private TextViewer textViewer;

    private final Sprite logoSprite;

    public CreditsViewer(Credits model) throws IOException {
        super(model);
        this.textViewer = new GameTextViewer();
        this.logoSprite = loadLogo();
    }

    public Sprite loadLogo() throws IOException {
        return new Sprite("menu/logo.png");
    }

    public static final TextColor backgroundColor = new TextColor.RGB(28, 28, 46);
    public static final TextColor messageColor = new TextColor.RGB(234, 234, 234);
    public static final TextColor nameColor = new TextColor.RGB(155,173,183);
    public static final TextColor scoreColor = new TextColor.RGB(91,110,225);
    public static final TextColor deathColor = new TextColor.RGB(95,133,240);
    public static final TextColor timeColor = new TextColor.RGB(99,155,255);
    public static final TextColor frameColor = new TextColor.RGB(255, 255, 255);

    @Override
    public void draw(ResizableGUI gui, long frameCount) throws IOException {
        gui.clear();
        drawBackgroundAndFrame(gui);
        drawMessages(gui);
        drawNames(gui);
        drawScore(gui);
        drawDeaths(gui);
        drawDuration(gui);
        logoSprite.draw(gui, 44, 16);
        gui.refresh();
    }

    public void setTextViewer(TextViewer textViewer){
        this.textViewer = textViewer;
    }


    private void drawBackgroundAndFrame(GUI gui) {
        gui.drawRectangle(1, 1, gui.getWidth() - 2, gui.getHeight() - 2, backgroundColor);
        gui.drawRectangle(0, 0, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, gui.getHeight() - 1, gui.getWidth(), 1, frameColor);
        gui.drawRectangle(0, 1, 1, gui.getHeight() - 2, frameColor);
        gui.drawRectangle(gui.getWidth() - 1, 1, 1, gui.getHeight() - 2, frameColor);
    }

    private void drawMessages(GUI gui) {
        int yAlignment = 6;
        int spacing = getCharHeight() * 8;
        for (int idx = 0; idx < getModel().getMessages().length ; idx++){
            String message = getModel().getMessages()[idx];
            int messageLength = message.length() * getCharWidth() + (message.length() - 1) * getSpacing();
            textViewer.draw(message,
                    (gui.getWidth() / 2) - (messageLength / 2),
                    yAlignment + spacing * idx,
                    messageColor, gui);
        }

    }

    private void drawNames(GUI gui) {
        int xAlignment = 95;
        int yAlignment = 60;
        int spacing = getCharHeight() * 2;
        for (int idx = 0; idx < getModel().getNames().length ; idx++){
            textViewer.draw(getModel().getNames()[idx],
                    xAlignment,
                    yAlignment + spacing * idx,
                    nameColor, gui);
        }
    }

    private void drawScore(GUI gui) {
        int xAlignment = 10;
        int yAlignment = 60;
        textViewer.draw("Score:  " + String.format("%1$" + 2 + "s", getModel().getScore()).replace(' ', '0'),
                    xAlignment,
                    yAlignment,
                    scoreColor, gui);
    }

    private void drawDeaths(GUI gui) {
        int xAlignment = 10;
        int yAlignment = 70;
        textViewer.draw("Deaths: " + String.format("%1$" + 2 + "s", getModel().getDeaths()).replace(' ', '0'),
                xAlignment,
                yAlignment,
                deathColor, gui);
    }


    private void drawDuration(GUI gui) {
        int xAlignment = 10;
        int yAlignment = 80;
        textViewer.draw("Time:   " + String.format("%1$" + 2 + "s", getModel().getMinutes()).replace(' ', '0')
                + ":" + String.format("%1$" + 2 + "s", getModel().getSeconds()).replace(' ', '0'),
                xAlignment,
                yAlignment,
                timeColor, gui);
    }


}

