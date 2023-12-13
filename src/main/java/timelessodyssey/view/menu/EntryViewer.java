package timelessodyssey.view.menu;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.gui.LanternaGUI;
import timelessodyssey.gui.ScreenCreator;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.view.text.GameTextViewer;
import timelessodyssey.view.text.TextViewer;

import java.io.IOException;

public class EntryViewer {
    private final TextViewer textViewer;

    public EntryViewer() throws IOException {
        this.textViewer = new GameTextViewer();
    }

    public void draw(Entry model, GUI gui, TextColor color) {
        String entryText = switch (model.getType()) {
            case START_GAME -> "Start";
            case SETTINGS -> "Settings";
            case EXIT -> "Exit";
            case RESOLUTION -> getResolutionLabel(gui);
            case TO_MAIN_MENU -> "Go Back";
        };
        textViewer.draw(entryText, (int) model.getPosition().x(), (int) model.getPosition().y(), color, gui);
    }

    private String getResolutionLabel(GUI gui) {
        if (gui instanceof LanternaGUI lanternaGUI) {
            final ScreenCreator.Resolution[] resolutions = ScreenCreator.Resolution.values();
            if (lanternaGUI.getResolution() == null)
                return "Resolution:   Automatic >";

            return String.format(
                    "Resolution: < %dX%d %c ",
                    lanternaGUI.getResolution().getWidth(),
                    lanternaGUI.getResolution().getHeight(),
                    resolutions[resolutions.length - 1] == lanternaGUI.getResolution() ? ' ' : '>'
            );
        }
        return "Resolution:   Unknown";
    }
}
