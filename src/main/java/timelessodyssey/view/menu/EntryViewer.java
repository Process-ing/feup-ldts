package timelessodyssey.view.menu;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.ResizableGUI;
import timelessodyssey.model.menu.Entry;
import timelessodyssey.view.text.TextViewer;

public class EntryViewer {
    private final TextViewer textViewer;

    public EntryViewer(TextViewer textViewer) {
        this.textViewer = textViewer;
    }

    public void draw(Entry model, ResizableGUI gui, TextColor color) {
        String entryText = switch (model.getType()) {
            case START_GAME -> "Start";
            case SETTINGS -> "Settings";
            case EXIT -> "Exit";
            case RESOLUTION -> getResolutionLabel(gui);
            case TO_MAIN_MENU -> "Go Back";
        };
        textViewer.draw(entryText, (int) model.getPosition().x(), (int) model.getPosition().y(), color, gui);
    }

    private String getResolutionLabel(ResizableGUI gui) {
        final ResizableGUI.Resolution[] resolutions = ResizableGUI.Resolution.values();
        if (gui.getResolution() == null)
            return "Resolution:   Automatic >";

        return String.format(
                "Resolution: < %dX%d %c ",
                gui.getResolution().getWidth(),
                gui.getResolution().getHeight(),
                resolutions[resolutions.length - 1] == gui.getResolution() ? ' ' : '>'
        );
    }
}
