package timelessodyssey.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LanternaKeyAdapter extends KeyAdapter {
    LanternaGUI gui;

    public LanternaKeyAdapter(LanternaGUI gui) {
        this.gui = gui;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gui.onKeyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        gui.onKeyReleased(e);
    }
}
