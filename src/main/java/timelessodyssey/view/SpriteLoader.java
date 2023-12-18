package timelessodyssey.view;

import java.io.IOException;

public interface SpriteLoader {
    Sprite get(String spriteFilepath) throws IOException;
}
