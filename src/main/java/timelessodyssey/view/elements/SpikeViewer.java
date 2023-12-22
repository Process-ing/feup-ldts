package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpikeViewer implements ElementViewer<Spike> {
    private final Map<Character, Sprite> spikeMap;

    public SpikeViewer(SpriteLoader spriteLoader) throws IOException {
        spikeMap = new HashMap<>();
        spikeMap.put('^', spriteLoader.get("sprites/spikes/futuristic/Bottom_Spike.png"));
        spikeMap.put('+', spriteLoader.get("sprites/spikes/cave/Bottom_Spike1.png"));
        spikeMap.put('-', spriteLoader.get("sprites/spikes/cave/Bottom_Spike2.png"));
    }

    @Override
    public void draw(Spike model, GUI gui, long frameCount) {
        Sprite sprite = spikeMap.get(model.getCharacter());
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
