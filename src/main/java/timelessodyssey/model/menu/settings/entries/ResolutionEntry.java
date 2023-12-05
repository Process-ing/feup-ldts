package timelessodyssey.model.menu.settings.entries;

import timelessodyssey.Game;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.menu.Entry;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class ResolutionEntry extends Entry {
    private static final GUI.Resolution[] resolutions = GUI.Resolution.values();

    public ResolutionEntry(int x, int y) {
        super(x, y);
    }

    @Override
    protected String createEntryText() {
        return null;
    }

    private String generateText(GUI.Resolution resolution) {
        if (resolution == null)
            return "Resolution:   Automatic >";
        return String.format(
            "Resolution: < %dX%d %c",
            resolution.getWidth(),
            resolution.getHeight(),
            resolutions[resolutions.length - 1] == resolution ? ' ' : '>'
        );
    }

    @Override
    public void doAction(Game game, GUI gui, GUI.Action action) throws IOException, URISyntaxException, FontFormatException {
        if (getText() == null)
            setText(generateText(gui.getResolution()));

        int index;
        switch (action) {
            case RIGHT:
                index = getResolutionIndex(gui.getResolution());
                if (index < resolutions.length - 1) {
                    GUI.Resolution newResolution = resolutions[index + 1];
                    gui.setResolution(newResolution);
                    setText(generateText(newResolution));
                }
                break;
            case LEFT:
                index = getResolutionIndex(gui.getResolution());
                if (index > -1) {
                    GUI.Resolution newResolution = index != 0 ? resolutions[index - 1] : null;
                    gui.setResolution(newResolution);
                    setText(generateText(newResolution));
                }
                break;
            default:
        }
    }

    private Integer getResolutionIndex(GUI.Resolution resolution) {
        for (int i = 0; i < GUI.Resolution.values().length; i++) {
            if (GUI.Resolution.values()[i] == resolution)
                return i;
        }
        return -1;
    }
}
