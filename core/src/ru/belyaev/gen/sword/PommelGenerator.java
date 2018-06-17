package ru.belyaev.gen.sword;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.google.common.primitives.Ints;

import static ru.belyaev.gen.sword.utils.PositionUtils.center;

public class PommelGenerator {
    private static final int MIN_POMMEL_WIDTH = 0; // отсутстует
    private static final int MAX_POMMEL_WIDTH = 32;

    private static final int MIN_POMMEL_HEIGHT = 8;
    private static final int MAX_POMMEL_HEIGHT = 32;

    public Pixmap generate(int w, int h) {
        final Pixmap result = new Pixmap(w, h, Pixmap.Format.RGBA8888);
        result.setColor(Color.CLEAR);
        result.fill();

        if(SwordGenerator.DEBUG) {
            result.setColor(Color.BLUE);
            result.drawRectangle(0, 0, w, h);
        }

        result.setColor(Color.BLACK);
        oval(result, 0,0,w, h);
        result.setColor(Color.GRAY);
        oval(result, 3,3,w-6, h-6);
        //triangle(result,w,h);
        //result.drawRectangle(0, center(swordHeight, pommelHeight), pommelWidth, pommelHeight);
        return result;
    }

    private void oval(Pixmap result, int offsetX, int offsetY, int w, int h) {
        w--;
        h--;
        // result.fillCircle(w/2,h/2, Math.min(w/2,h/2)-1);
        final double a = Math.max(w / 2.d, h / 2.d);
        final double b = Math.min(w / 2.d, h / 2.d);
        final double k = b / a;
        final double e2 = 1 - k * k;
        final double c = a * Math.sqrt(e2);

        final Vector2 m = new Vector2();
        final Vector2 f1 = new Vector2();
        final Vector2 f2 = new Vector2();
        if (w > h) {
            f1.set(w / 2 - (int) c, h / 2);
            f2.set(w / 2 + (int) c, h / 2);
        } else {
            f1.set(w / 2, h / 2 - (int) c);
            f2.set(w / 2, h / 2 + (int) c);
        }
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                m.set(x, y);
                if (f1.dst(m) + f2.dst(m) <= 2 * a) {
                    result.drawPixel(x+offsetX, y+offsetY);
                }
            }
        }
    }

    private void triangle(Pixmap result, int w, int h) {
        result.drawLine(0, h / 2, w, 0);
        result.drawLine(0, h / 2, w, h);
        result.drawLine(w, 0, w, h);
    }


}
