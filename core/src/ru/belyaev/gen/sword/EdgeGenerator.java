package ru.belyaev.gen.sword;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import ru.belyaev.gen.sword.utils.PixmapUtils;

import static ru.belyaev.gen.sword.utils.PositionUtils.center;

public class EdgeGenerator {
    public Pixmap generate(int w, int h, int p) {
        final Pixmap result = new Pixmap(w, h, Pixmap.Format.RGBA8888);
        result.setColor(Color.CLEAR);
        result.fill();

        if (SwordGenerator.DEBUG) {
            result.setColor(Color.BLUE);
            result.drawRectangle(0, 0, w, h);
        }

        result.setColor(Color.BLACK);

        if(h%2 == 0) {
            final Vector2 a = new Vector2(0, 0);
            final Vector2 b = new Vector2(w - p, 0);
            final Vector2 c1 = new Vector2(w - 1, h / 2-1);
            final Vector2 c2 = new Vector2(w - 1, h / 2);
            final Vector2 d = new Vector2(w - p, h - 1);
            final Vector2 e = new Vector2(0, h - 1);
            PixmapUtils.drawLine(result, a, b, c1);
            PixmapUtils.drawLine(result, e, d, c2);
        } else {
            final Vector2 a = new Vector2(0, 0);
            final Vector2 b = new Vector2(w - p, 0);
            final Vector2 c = new Vector2(w - 1, h / 2);
            final Vector2 d = new Vector2(w - p, h - 1);
            final Vector2 e = new Vector2(0, h - 1);
            PixmapUtils.drawPoly(result, a, b, c, d, e);
        }

        final int w2 = 32;
        final int bladeSize = 2;
        final float colorModifier = 0.05f;

        Color baseColor = new Color(Color.LIGHT_GRAY);

        Color lightColor = new Color(baseColor);
        lightColor.add(colorModifier, colorModifier, colorModifier, 0);

        Color darkColor = new Color(baseColor);
        darkColor.add(-colorModifier, -colorModifier, -colorModifier, 0);

        Color darkColor2 = new Color(darkColor);
        darkColor2.add(-colorModifier, -colorModifier, -colorModifier, 0);

        result.setColor(baseColor);
        result.fillRectangle(1, 1, w2, h - 2);

        result.setColor(lightColor);
        result.fillRectangle(1, 1, w2, bladeSize);

        result.setColor(darkColor2);
        result.fillRectangle(1, h - bladeSize - 1, w2, bladeSize);

//        result.setColor(darkColor);
//        result.fillRectangle(1, center(h,2), 16, 2);

        return result;
    }
}
