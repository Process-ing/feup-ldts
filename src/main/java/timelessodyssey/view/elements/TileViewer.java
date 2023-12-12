package timelessodyssey.view.elements;

import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.elements.Tile;
import timelessodyssey.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileViewer implements ElementViewer<Tile>  {
    private final Map<Character, Sprite> tileMap;
    {
        tileMap = new HashMap<>();
        try {
            tileMap.put('G', new Sprite("sprites/tiles/futuristic/Gray.png"));
            tileMap.put('K', new Sprite("sprites/tiles/futuristic/Gray_Spots.png"));
            tileMap.put('T', new Sprite("sprites/tiles/futuristic/ground/Top.png"));
            tileMap.put('L', new Sprite("sprites/tiles/futuristic/ground/Left.png"));
            tileMap.put('R', new Sprite("sprites/tiles/futuristic/ground/Right.png"));
            tileMap.put('B', new Sprite("sprites/tiles/futuristic/ground/Bottom.png"));
            tileMap.put('Z', new Sprite("sprites/tiles/futuristic/ground/Top_Left.png"));
            tileMap.put('X', new Sprite("sprites/tiles/futuristic/ground/Top_Right.png"));
            tileMap.put('C', new Sprite("sprites/tiles/futuristic/ground/Bottom_Left.png"));
            tileMap.put('V', new Sprite("sprites/tiles/futuristic/ground/Bottom_Right.png"));
            tileMap.put('A', new Sprite("sprites/tiles/futuristic/ground/Top_Left_Corner.png"));
            tileMap.put('S', new Sprite("sprites/tiles/futuristic/ground/Top_Right_Corner.png"));
            tileMap.put('D', new Sprite("sprites/tiles/futuristic/ground/Bottom_Left_Corner.png"));
            tileMap.put('F', new Sprite("sprites/tiles/futuristic/ground/Bottom_Right_Corner.png"));
            tileMap.put('M', new Sprite("sprites/tiles/futuristic/metal/Metal.png"));
            tileMap.put('I', new Sprite("sprites/tiles/futuristic/metal/Vertical_Metal.png"));
            tileMap.put('O', new Sprite("sprites/tiles/futuristic/metal/Horizontal_Metal.png"));
            tileMap.put('1', new Sprite("sprites/tiles/futuristic/pipes/Top.png"));
            tileMap.put('2', new Sprite("sprites/tiles/futuristic/pipes/Left.png"));
            tileMap.put('3', new Sprite("sprites/tiles/futuristic/pipes/Right.png"));
            tileMap.put('4', new Sprite("sprites/tiles/futuristic/pipes/Bottom.png"));
            tileMap.put('5', new Sprite("sprites/tiles/futuristic/pipes/Top_Left.png"));
            tileMap.put('6', new Sprite("sprites/tiles/futuristic/pipes/Top_Right.png"));
            tileMap.put('7', new Sprite("sprites/tiles/futuristic/pipes/Bottom_Left.png"));
            tileMap.put('8', new Sprite("sprites/tiles/futuristic/pipes/Bottom_Right.png"));
            tileMap.put('9', new Sprite("sprites/tiles/futuristic/pipes/Vertical.png"));
            tileMap.put('0', new Sprite("sprites/tiles/futuristic/pipes/Horizontal.png"));
            tileMap.put('g', new Sprite("sprites/tiles/cave/background/Dirt.png"));
            tileMap.put('k', new Sprite("sprites/tiles/cave/background/Rock.png"));
            tileMap.put('m', new Sprite("sprites/tiles/cave/background/Coal.png"));
            tileMap.put('n', new Sprite("sprites/tiles/cave/background/Gold.png"));
            tileMap.put('u', new Sprite("sprites/tiles/cave/background/Diamond.png"));
            tileMap.put('t', new Sprite("sprites/tiles/cave/ground/Top.png"));
            tileMap.put('l', new Sprite("sprites/tiles/cave/ground/Left.png"));
            tileMap.put('r', new Sprite("sprites/tiles/cave/ground/Right.png"));
            tileMap.put('b', new Sprite("sprites/tiles/cave/ground/Bottom.png"));
            tileMap.put('z', new Sprite("sprites/tiles/cave/ground/Top_Left.png"));
            tileMap.put('x', new Sprite("sprites/tiles/cave/ground/Top_Right.png"));
            tileMap.put('c', new Sprite("sprites/tiles/cave/ground/Bottom_Left.png"));
            tileMap.put('v', new Sprite("sprites/tiles/cave/ground/Bottom_Right.png"));
            tileMap.put('a', new Sprite("sprites/tiles/cave/ground/Top_Left_Corner.png"));
            tileMap.put('s', new Sprite("sprites/tiles/cave/ground/Top_Right_Corner.png"));
            tileMap.put('d', new Sprite("sprites/tiles/cave/ground/Bottom_Left_Corner.png"));
            tileMap.put('f', new Sprite("sprites/tiles/cave/ground/Bottom_Right_Corner.png"));
            tileMap.put('W', new Sprite("sprites/tiles/goals/Cake.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(Tile model, GUI gui, long frameCount) throws IOException {
        Sprite sprite = tileMap.get(model.getCharacter());
        sprite.draw(gui, model.getPosition().x(), model.getPosition().y());
    }
}
