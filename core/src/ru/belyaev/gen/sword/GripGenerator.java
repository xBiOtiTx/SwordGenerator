package ru.belyaev.gen.sword;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import ru.belyaev.gen.sword.utils.PixmapUtils;

import static ru.belyaev.gen.sword.utils.PositionUtils.center;

public class GripGenerator {
    public Pixmap generate(int w, int h1, int h2) {
        final int h = Math.max(h1, h2);
        final Pixmap result = new Pixmap(w, h, Pixmap.Format.RGBA8888);
        result.setColor(Color.CLEAR);
        result.fill();

        if (SwordGenerator.DEBUG) {
            result.setColor(Color.BLUE);
            result.drawRectangle(0, 0, w, h);
        }

        result.setColor(Color.BLACK);

        final int c1 = center(h, h1);
        final int c2 = center(h, h2);

        final Vector2 a = new Vector2(0, c1);
        final Vector2 b = new Vector2(0, c1 + h1 - 1);

        final Vector2 c = new Vector2(w - 1, c2);
        final Vector2 d = new Vector2(w - 1, c2 + h2 - 1);

        PixmapUtils.drawPoly(result, a, b, d, c);

        Color t0 = new Color();
        Color t1 = new Color();
        result.setColor(Color.BROWN);
        for (int x = 0; x < w; x++) {
            boolean draw = false;
            t0.set(result.getPixel(x, 0));
            for (int y = 1; y < h; y++) {
                t1.set(result.getPixel(x, y));
                if (!t0.equals(t1) && t0.equals(Color.BLACK)) {
                    draw = !draw;
                }
                if (draw && !t1.equals(Color.BLACK)) {
                    result.drawPixel(x, y);
                }
                t0.set(t1);
            }
        }

        return result;
    }
}
