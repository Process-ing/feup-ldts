package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.player.*;
import timelessodyssey.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerViewer implements ElementViewer<Player> {
    private final Map<Class<?>, Sprite[][]> spriteMap;

    public PlayerViewer() throws IOException {
        spriteMap = new HashMap<>();
        spriteMap.put(IdleState.class, new Sprite[][] {
            { new Sprite("sprites/player/player-left-1.png") },
            { new Sprite("sprites/player/player-right-1.png") },
        });
        spriteMap.put(WalkingState.class, new Sprite[][] {
            {
                new Sprite("sprites/player/player-left-2.png"),
                new Sprite("sprites/player/player-left-3.png"),
            },
            {
                new Sprite("sprites/player/player-right-2.png"),
                new Sprite("sprites/player/player-right-3.png")
            },
        });
        spriteMap.put(RunningState.class, new Sprite[][] {
            {
                new Sprite("sprites/player/player-left-4.png"),
                new Sprite("sprites/player/player-left-5.png"),
            },
            {
                new Sprite("sprites/player/player-right-4.png"),
                new Sprite("sprites/player/player-right-5.png")
            },
        });
        spriteMap.put(JumpingState.class, new Sprite[][] {
                { new Sprite("sprites/player/player-left-6.png") },
                { new Sprite("sprites/player/player-right-6.png") },
        });
        spriteMap.put(FallingState.class, new Sprite[][] {
                { new Sprite("sprites/player/player-left-7.png") },
                { new Sprite("sprites/player/player-right-7.png") },
        });
        spriteMap.put(DashingState.class, new Sprite[][] {
                { new Sprite("sprites/player/player-left-4.png") },
                { new Sprite("sprites/player/player-right-4.png") },
        });
        spriteMap.put(AfterDashState.class, new Sprite[][] {
                { new Sprite("sprites/player/player-left-7.png") },
                { new Sprite("sprites/player/player-right-7.png") },
        });
    }

    @Override
    public void draw(Player model, GUI gui, long frameCount) {
        getSprite(model, frameCount).draw(gui, model.getPosition().x(), model.getPosition().y());
    }

    private Sprite getSprite(Player model, long frameCount) {
        Sprite[][] spriteSequencePair = spriteMap.get(model.getState().getClass());
        Sprite[] spriteSequence = spriteSequencePair[model.isFacingRight() ? 1 : 0];
        return spriteSequence[(int)(frameCount % spriteSequence.length)];
    }
}
