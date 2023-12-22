package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.player.*;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerViewer implements ElementViewer<Player> {
    private final Map<Class<?>, Sprite[][]> spriteMap;

    public PlayerViewer(SpriteLoader spriteLoader) throws IOException {
        spriteMap = new HashMap<>();
        spriteMap.put(IdleState.class, new Sprite[][] {
            { spriteLoader.get("sprites/player/player-left-1.png") },
            { spriteLoader.get("sprites/player/player-right-1.png") },
        });
        spriteMap.put(WalkingState.class, new Sprite[][] {
            {
                spriteLoader.get("sprites/player/player-left-2.png"),
                spriteLoader.get("sprites/player/player-left-3.png"),
            },
            {
                spriteLoader.get("sprites/player/player-right-2.png"),
                spriteLoader.get("sprites/player/player-right-3.png")
            },
        });
        spriteMap.put(RunningState.class, new Sprite[][] {
            {
                spriteLoader.get("sprites/player/player-left-4.png"),
                spriteLoader.get("sprites/player/player-left-5.png"),
            },
            {
                spriteLoader.get("sprites/player/player-right-4.png"),
                spriteLoader.get("sprites/player/player-right-5.png")
            },
        });
        spriteMap.put(JumpingState.class, new Sprite[][] {
                { spriteLoader.get("sprites/player/player-left-6.png") },
                { spriteLoader.get("sprites/player/player-right-6.png") },
        });
        spriteMap.put(FallingState.class, new Sprite[][] {
                { spriteLoader.get("sprites/player/player-left-7.png") },
                { spriteLoader.get("sprites/player/player-right-7.png") },
        });
        spriteMap.put(DashingState.class, new Sprite[][] {
                { spriteLoader.get("sprites/player/player-left-4.png") },
                { spriteLoader.get("sprites/player/player-right-4.png") },
        });
        spriteMap.put(AfterDashState.class, new Sprite[][] {
                { spriteLoader.get("sprites/player/player-left-7.png") },
                { spriteLoader.get("sprites/player/player-right-7.png") },
        });
        spriteMap.put(DeadState.class, new Sprite[][] { { null }, { null } });
    }

    @Override
    public void draw(Player model, GUI gui, long frameCount) {
        Sprite sprite = getSprite(model, frameCount);
        if (sprite != null)
            sprite.draw(gui, model.getPosition().x() + (model.isFacingRight() ? -2 : 0), model.getPosition().y());
    }

    private Sprite getSprite(Player model, long frameCount) {
        Sprite[][] spriteSequencePair = spriteMap.get(model.getState().getClass());
        Sprite[] spriteSequence = spriteSequencePair[model.isFacingRight() ? 1 : 0];
        return spriteSequence[(int)(frameCount % spriteSequence.length)];
    }
}
