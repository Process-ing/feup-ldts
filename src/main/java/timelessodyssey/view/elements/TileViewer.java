package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileViewer implements ElementViewer<Tile>  {
    private static final Map<Character, String> tileMap;
    static {
        tileMap = new HashMap<>();
        tileMap.put('G', "sprites/tiles/futuristic/Gray.png");
        tileMap.put('K', "sprites/tiles/futuristic/Gray_Spots.png");
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
        tileMap.put('M', "sprites/tiles/futuristic/metal/Metal.png");
        tileMap.put('I', "sprites/tiles/futuristic/metal/Vertical_Metal.png");
        tileMap.put('O', "sprites/tiles/futuristic/metal/Horizontal_Metal.png");
        tileMap.put('1', "sprites/tiles/futuristic/pipes/Top.png");
        tileMap.put('2', "sprites/tiles/futuristic/pipes/Left.png");
        tileMap.put('3', "sprites/tiles/futuristic/pipes/Right.png");
        tileMap.put('4', "sprites/tiles/futuristic/pipes/Bottom.png");
        tileMap.put('5', "sprites/tiles/futuristic/pipes/Top_Left.png");
        tileMap.put('6', "sprites/tiles/futuristic/pipes/Top_Right.png");
        tileMap.put('7', "sprites/tiles/futuristic/pipes/Bottom_Left.png");
        tileMap.put('8', "sprites/tiles/futuristic/pipes/Bottom_Right.png");
        tileMap.put('9', "sprites/tiles/futuristic/pipes/Vertical.png");
        tileMap.put('0', "sprites/tiles/futuristic/pipes/Horizontal.png");
        tileMap.put('g', "sprites/tiles/cave/background/Dirt.png");
        tileMap.put('k', "sprites/tiles/cave/background/Rock.png");
        tileMap.put('m', "sprites/tiles/cave/background/Coal.png");
        tileMap.put('n', "sprites/tiles/cave/background/Gold.png");
        tileMap.put('u', "sprites/tiles/cave/background/Diamond.png");
        tileMap.put('t', "sprites/tiles/cave/ground/Top.png");
        tileMap.put('l', "sprites/tiles/cave/ground/Left.png");
        tileMap.put('r', "sprites/tiles/cave/ground/Right.png");
        tileMap.put('b', "sprites/tiles/cave/ground/Bottom.png");
        tileMap.put('z', "sprites/tiles/cave/ground/Top_Left.png");
        tileMap.put('x', "sprites/tiles/cave/ground/Top_Right.png");
        tileMap.put('c', "sprites/tiles/cave/ground/Bottom_Left.png");
        tileMap.put('v', "sprites/tiles/cave/ground/Bottom_Right.png");
        tileMap.put('a', "sprites/tiles/cave/ground/Top_Left_Corner.png");
        tileMap.put('s', "sprites/tiles/cave/ground/Top_Right_Corner.png");
        tileMap.put('d', "sprites/tiles/cave/ground/Bottom_Left_Corner.png");
        tileMap.put('f', "sprites/tiles/cave/ground/Bottom_Right_Corner.png");
        tileMap.put('i', "sprites/tiles/cave/ground/Top_Spike.png");
        tileMap.put('o', "sprites/tiles/cave/ground/Bottom_Spike.png");
    }

    @Override
    public void draw(Tile model, GUI gui) throws IOException {
        Sprite sprite = new Sprite(tileMap.get(model.getCharacter()));
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
