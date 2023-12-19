package timelessodyssey.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class GameSpriteLoader implements SpriteLoader {
    final Map<String, Sprite> spriteMap;

    public GameSpriteLoader() {
        spriteMap = new HashMap<>();
    }

    @Override
    public Sprite get(String spriteFilepath) throws IOException {
        if (!spriteMap.containsKey(spriteFilepath))
            spriteMap.put(spriteFilepath, new Sprite(spriteFilepath));
        return spriteMap.get(spriteFilepath);
    }
}
