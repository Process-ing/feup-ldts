package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Spike;
import timelessodyssey.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpikeViewer implements ElementViewer<Spike> {
    private final Map<Character, Sprite> spikeMap;
    {
        spikeMap = new HashMap<>();
        try {
            spikeMap.put('^', new Sprite("sprites/spikes/futuristic/Bottom_Spike.png"));
            spikeMap.put('+', new Sprite("sprites/spikes/cave/Bottom_Spike1.png"));
            spikeMap.put('-', new Sprite("sprites/spikes/cave/Bottom_Spike2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void draw(Spike model, GUI gui, long frameCount) throws IOException {
        Sprite sprite = spikeMap.get(model.getCharacter());
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
