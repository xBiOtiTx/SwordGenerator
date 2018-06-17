package ru.belyaev.gen.sword;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import ru.belyaev.gen.sword.utils.PixmapUtils;

import static ru.belyaev.gen.sword.utils.PositionUtils.center;

public class CrossGuardGenerator {
    public Pixmap generate(int w, int h, int c) {
        final Pixmap result = new Pixmap(w, h, Pixmap.Format.RGBA8888);
        result.setColor(Color.CLEAR);
        result.fill();

        if(SwordGenerator.DEBUG) {
            result.setColor(Color.BLUE);
            result.drawRectangle(0, 0, w, h);
        }

        result.setColor(Color.DARK_GRAY);
        result.fillRectangle(0,0,w,c);

        result.setColor(Color.GRAY);
        result.fillRectangle(0,c,w,h-c*2);

        result.setColor(Color.DARK_GRAY);
        result.fillRectangle(0, h - c, w, c);

        result.setColor(Color.BLACK);
        result.drawRectangle(0, 0, w, h);
        result.drawRectangle(0, 0, w, c);
        result.drawRectangle(0, h - c, w, c);

        return result;
    }
}
