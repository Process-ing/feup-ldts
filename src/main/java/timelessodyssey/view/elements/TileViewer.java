package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.view.Sprite;
import timelessodyssey.view.SpriteLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileViewer implements ElementViewer<Tile>  {
    private final Map<Character, Sprite> tileMap;

    public TileViewer(SpriteLoader spriteLoader) throws IOException {
        tileMap = new HashMap<>();
        tileMap.put('G', spriteLoader.get("sprites/tiles/futuristic/Gray.png"));
        tileMap.put('K', spriteLoader.get("sprites/tiles/futuristic/Gray_Spots.png"));
        tileMap.put('T', spriteLoader.get("sprites/tiles/futuristic/ground/Top.png"));
        tileMap.put('L', spriteLoader.get("sprites/tiles/futuristic/ground/Left.png"));
        tileMap.put('R', spriteLoader.get("sprites/tiles/futuristic/ground/Right.png"));
        tileMap.put('B', spriteLoader.get("sprites/tiles/futuristic/ground/Bottom.png"));
        tileMap.put('Z', spriteLoader.get("sprites/tiles/futuristic/ground/Top_Left.png"));
        tileMap.put('X', spriteLoader.get("sprites/tiles/futuristic/ground/Top_Right.png"));
        tileMap.put('C', spriteLoader.get("sprites/tiles/futuristic/ground/Bottom_Left.png"));
        tileMap.put('V', spriteLoader.get("sprites/tiles/futuristic/ground/Bottom_Right.png"));
        tileMap.put('A', spriteLoader.get("sprites/tiles/futuristic/ground/Top_Left_Corner.png"));
        tileMap.put('S', spriteLoader.get("sprites/tiles/futuristic/ground/Top_Right_Corner.png"));
        tileMap.put('D', spriteLoader.get("sprites/tiles/futuristic/ground/Bottom_Left_Corner.png"));
        tileMap.put('F', spriteLoader.get("sprites/tiles/futuristic/ground/Bottom_Right_Corner.png"));
        tileMap.put('M', spriteLoader.get("sprites/tiles/futuristic/metal/Metal.png"));
        tileMap.put('I', spriteLoader.get("sprites/tiles/futuristic/metal/Vertical_Metal.png"));
        tileMap.put('O', spriteLoader.get("sprites/tiles/futuristic/metal/Horizontal_Metal.png"));
        tileMap.put('1', spriteLoader.get("sprites/tiles/futuristic/pipes/Top.png"));
        tileMap.put('2', spriteLoader.get("sprites/tiles/futuristic/pipes/Left.png"));
        tileMap.put('3', spriteLoader.get("sprites/tiles/futuristic/pipes/Right.png"));
        tileMap.put('4', spriteLoader.get("sprites/tiles/futuristic/pipes/Bottom.png"));
        tileMap.put('5', spriteLoader.get("sprites/tiles/futuristic/pipes/Top_Left.png"));
        tileMap.put('6', spriteLoader.get("sprites/tiles/futuristic/pipes/Top_Right.png"));
        tileMap.put('7', spriteLoader.get("sprites/tiles/futuristic/pipes/Bottom_Left.png"));
        tileMap.put('8', spriteLoader.get("sprites/tiles/futuristic/pipes/Bottom_Right.png"));
        tileMap.put('9', spriteLoader.get("sprites/tiles/futuristic/pipes/Vertical.png"));
        tileMap.put('0', spriteLoader.get("sprites/tiles/futuristic/pipes/Horizontal.png"));
        tileMap.put('g', spriteLoader.get("sprites/tiles/cave/background/Dirt.png"));
        tileMap.put('k', spriteLoader.get("sprites/tiles/cave/background/Rock.png"));
        tileMap.put('m', spriteLoader.get("sprites/tiles/cave/background/Coal.png"));
        tileMap.put('n', spriteLoader.get("sprites/tiles/cave/background/Gold.png"));
        tileMap.put('u', spriteLoader.get("sprites/tiles/cave/background/Diamond.png"));
        tileMap.put('t', spriteLoader.get("sprites/tiles/cave/ground/Top.png"));
        tileMap.put('l', spriteLoader.get("sprites/tiles/cave/ground/Left.png"));
        tileMap.put('r', spriteLoader.get("sprites/tiles/cave/ground/Right.png"));
        tileMap.put('b', spriteLoader.get("sprites/tiles/cave/ground/Bottom.png"));
        tileMap.put('z', spriteLoader.get("sprites/tiles/cave/ground/Top_Left.png"));
        tileMap.put('x', spriteLoader.get("sprites/tiles/cave/ground/Top_Right.png"));
        tileMap.put('c', spriteLoader.get("sprites/tiles/cave/ground/Bottom_Left.png"));
        tileMap.put('v', spriteLoader.get("sprites/tiles/cave/ground/Bottom_Right.png"));
        tileMap.put('a', spriteLoader.get("sprites/tiles/cave/ground/Top_Left_Corner.png"));
        tileMap.put('s', spriteLoader.get("sprites/tiles/cave/ground/Top_Right_Corner.png"));
        tileMap.put('d', spriteLoader.get("sprites/tiles/cave/ground/Bottom_Left_Corner.png"));
        tileMap.put('f', spriteLoader.get("sprites/tiles/cave/ground/Bottom_Right_Corner.png"));
        tileMap.put('W', spriteLoader.get("sprites/tiles/goals/Cake.png"));
    }

    @Override
    public void draw(Tile model, GUI gui, long frameCount) {
        Sprite sprite = tileMap.get(model.getCharacter());
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
