package timelessodyssey.view.text;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

record CharPosition(int row, int col) {}

public class GameTextViewer implements TextViewer {
    private static final int charWidth = 3;
    private static final int charHeight = 5;
    private static final int spacing = 1;

    private final BufferedImage fontImage;
    private final Map<Character, CharPosition> charMap;

    public GameTextViewer() throws IOException {
        URL resource = getClass().getClassLoader().getResource("gamefont/font.png");
        this.fontImage = ImageIO.read(Objects.requireNonNull(resource));
        this.charMap = parseCharMap();
    }

    private Map<Character, CharPosition> parseCharMap() throws IOException {
        Map<Character, CharPosition> charMap = new HashMap<>();
        URL resource = getClass().getClassLoader().getResource("gamefont/font-map.txt");
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(Objects.requireNonNull(resource).getFile()), UTF_8);

        int y = 0;
        for (String line; (line = bufferedReader.readLine()) != null; y++) {
            for (int x = 0; x < line.length(); x++)
                charMap.put(line.charAt(x), new CharPosition(x, y));
        }

        return charMap;
    }

    @Override
    public void draw(char character, double x, double y, TextColor foregroundColor, GUI gui) {
        if (charMap.containsKey(character)) {
            CharPosition charPosition = charMap.get(character);
            drawKnownChar(charPosition, x, y, foregroundColor, gui);
        } else {
            drawUnknownChar(x, y, foregroundColor, gui);
        }
    }

    private void drawKnownChar(CharPosition position, double x, double y, TextColor foregroundColor, GUI gui) {
        final int COLOR_WHITE = 0xFFFFFFFF;
        int imgX = position.row() * (charWidth + 1);
        int imgY = position.col() * (charHeight + 1);
        for (int dy = 0; dy < charHeight; dy++) {
            for (int dx = 0; dx < charWidth; dx++) {
                if (fontImage.getRGB(imgX + dx, imgY + dy) != COLOR_WHITE)
                    gui.drawPixel(x + dx, y + dy, foregroundColor);
            }
        }
    }

    private void drawUnknownChar(double x, double y, TextColor foregroundColor, GUI gui) {
        gui.drawRectangle(x, y, charWidth, charHeight, foregroundColor);
    }

    @Override
    public void draw(String string, double x, double y, TextColor foregroundColor, GUI gui) {
        for (int i = 0; i < string.length(); i++) {
            int xOffset = i * (charWidth + spacing);
            draw(string.charAt(i), x + xOffset, y, foregroundColor, gui);
        }
    }

    public static int getCharHeight() {
        return charHeight;
    }

    public static int getCharWidth() {
        return charWidth;
    }

    public static int getSpacing() {
        return spacing;
    }
}
