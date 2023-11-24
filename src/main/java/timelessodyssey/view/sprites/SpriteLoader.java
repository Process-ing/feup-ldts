package timelessodyssey.view.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SpriteLoader {

    public Sprite createSprite(String spritePath) throws IOException {
        URL resource = getClass().getClassLoader().getResource(spritePath);
        BufferedImage image = ImageIO.read(new File(resource.getFile()));
        return new Sprite(image);
    }
}
