package timelessodyssey.view.elements;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileViewer implements ElementViewer<Tile>  {
    private static final Map<Character, String> tileMap;
    static {
        tileMap = new HashMap<Character, String>();
        tileMap.put('#', "sprites/tiles/futuristic/Gray.png");
        tileMap.put('$', "sprites/tiles/futuristic/Gray_Spots.png");
        tileMap.put('T', "sprites/tiles/futuristic/ground/Top.png");
        tileMap.put('L', "sprites/tiles/futuristic/ground/Left.png");
        tileMap.put('R', "sprites/tiles/futuristic/ground/Right.png");
        tileMap.put('B', "sprites/tiles/futuristic/ground/Bottom.png");
        tileMap.put('Z', "sprites/tiles/futuristic/ground/Top_Left.png");
        tileMap.put('X', "sprites/tiles/futuristic/ground/Top_Right.png");
        tileMap.put('C', "sprites/tiles/futuristic/ground/Bottom_Left.png");
        tileMap.put('V', "sprites/tiles/futuristic/ground/Bottom_Right.png");
        tileMap.put('A', "sprites/tiles/futuristic/ground/Top_Left_Corner.png");
        tileMap.put('S', "sprites/tiles/futuristic/ground/Top_Right_Corner.png");
        tileMap.put('D', "sprites/tiles/futuristic/ground/Bottom_Left_Corner.png");
        tileMap.put('F', "sprites/tiles/futuristic/ground/Bottom_Right_Corner.png");
        tileMap.put('*', "sprites/tiles/futuristic/metal/Metal.png");
        tileMap.put('|', "sprites/tiles/futuristic/metal/Vertical_Metal.png");
        tileMap.put('-', "sprites/tiles/futuristic/metal/Horizontal_Metal.png");

    }

    @Override
    public void draw(Tile model, GUI gui) throws IOException {
        Sprite sprite = new Sprite(tileMap.get(model.getCharacter()));
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
