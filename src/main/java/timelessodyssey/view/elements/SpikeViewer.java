package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpikeViewer implements ElementViewer<Spike> {
    private static final Map<Character, String> spikeMap;
    static {
        spikeMap = new HashMap<Character, String>();
        spikeMap.put('^', "sprites/spikes/futuristic/Bottom_Spike.png");
        spikeMap.put('_', "sprites/spikes/futuristic/Top_Spike.png");
    }
    @Override
    public void draw(Spike model, GUI gui) throws IOException {
        Sprite sprite = new Sprite(spikeMap.get(model.getCharacter()));
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
