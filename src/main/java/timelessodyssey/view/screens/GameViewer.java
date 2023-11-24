package timelessodyssey.view.screens;

import com.googlecode.lanterna.TextColor;
import timelessodyssey.gui.GUI;
import timelessodyssey.model.game.scene.Scene;

import java.io.IOException;

public class GameViewer extends ScreenViewer<Scene> {
    public GameViewer(Scene model) {
        super(model);
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        // COLOR 1: 255  205  178
        // COLOR 2: 109  104  117
        TextColor.RGB color1 = new TextColor.RGB(34, 87, 122);
        TextColor.RGB color2 = new TextColor.RGB(128, 237, 153);

        for (int w = 0; w < 160; w++){
            for (int h = 0; h<90; h++){
                gui.drawPixel(w,h, new TextColor.RGB(28,28,28));
            }
        }

        for (int w = 5; w < 155; w++){
            for (int h = 5; h<85; h++){
                gui.drawPixel(w,h, new TextColor.RGB(55,55,55));
            }
        }

        for (int w = 0; w < 160; w++){
            TextColor.RGB current = new TextColor.RGB(color1.getRed() + (color2.getRed() - color1.getRed())*w/160,
                                                      color1.getGreen() + (color2.getGreen() - color1.getGreen())*w/160,
                                                        color1.getBlue() + (color2.getBlue() - color1.getBlue())*w/160);
            TextColor.RGB opposite = new TextColor.RGB(color2.getRed() + (color1.getRed() - color2.getRed())*w/160,
                                                       color2.getGreen() + (color1.getGreen() - color2.getGreen())*w/160,
                                                       color2.getBlue() + (color1.getBlue() - color2.getBlue())*w/160);
            gui.drawPixel(w, 0, current);
            gui.drawPixel(w, 89, opposite);
        }

        for (int w = 5; w < 155; w++){
            TextColor.RGB current = new TextColor.RGB(color1.getRed() + (color2.getRed() - color1.getRed())*w/150,
                    color1.getGreen() + (color2.getGreen() - color1.getGreen())*w/150,
                    color1.getBlue() + (color2.getBlue() - color1.getBlue())*w/150);
            TextColor.RGB opposite = new TextColor.RGB(color2.getRed() + (color1.getRed() - color2.getRed())*w/150,
                    color2.getGreen() + (color1.getGreen() - color2.getGreen())*w/150,
                    color2.getBlue() + (color1.getBlue() - color2.getBlue())*w/150);
            gui.drawPixel(w, 5, opposite);
            gui.drawPixel(w, 84, current);
        }

        for (int h = 1; h < 90-1; h++){
            TextColor.RGB current = new TextColor.RGB(color1.getRed() + (color2.getRed() - color1.getRed())*h/90,
                    color1.getGreen() + (color2.getGreen() - color1.getGreen())*h/90,
                    color1.getBlue() + (color2.getBlue() - color1.getBlue())*h/90);
            TextColor.RGB opposite = new TextColor.RGB(color2.getRed() + (color1.getRed() - color2.getRed())*h/90,
                    color2.getGreen() + (color1.getGreen() - color2.getGreen())*h/90,
                    color2.getBlue() + (color1.getBlue() - color2.getBlue())*h/90);
            gui.drawPixel(0, h, current);
            gui.drawPixel(159, h, opposite);
        }

        for (int h = 5; h < 85-1; h++){
            TextColor.RGB current = new TextColor.RGB(color1.getRed() + (color2.getRed() - color1.getRed())*h/80,
                    color1.getGreen() + (color2.getGreen() - color1.getGreen())*h/80,
                    color1.getBlue() + (color2.getBlue() - color1.getBlue())*h/80);
            TextColor.RGB opposite = new TextColor.RGB(color2.getRed() + (color1.getRed() - color2.getRed())*h/80,
                    color2.getGreen() + (color1.getGreen() - color2.getGreen())*h/80,
                    color2.getBlue() + (color1.getBlue() - color2.getBlue())*h/80);
            gui.drawPixel(5, h, opposite);
            gui.drawPixel(154, h, current);
        }



        gui.refresh();
    }
}
