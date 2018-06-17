package ru.belyaev.gen.sword;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.google.common.primitives.Ints;

import static ru.belyaev.gen.sword.utils.PositionUtils.center;


/**
 * Created by Евгений on 05.06.2018.
 */
public class SwordGenerator {
    public static final boolean DEBUG = false;

    private static final int MIN_POMMEL_WIDTH = 0; // отсутстует
    private static final int MAX_POMMEL_WIDTH = 32;
    // ---
    private static final int MIN_POMMEL_HEIGHT = 8;
    private static final int MAX_POMMEL_HEIGHT = 32;
    // =================================================================
    private static final int MIN_GRIP_WIDTH = 8; // ручки не может не быть О_о
    private static final int MAX_GRIP_WIDTH = 64;
    // ---
    private static final int MIN_GRIP_HEIGHT = MIN_POMMEL_HEIGHT;
    private static final int MAX_GRIP_HEIGHT = MAX_POMMEL_HEIGHT;
    // =================================================================
    private static final int MIN_CROSS_GUARD_WIDTH = 0; // отсутствует
    private static final int MAX_CROSS_GUARD_WIDTH = 64;
    // ---
    private static final int MIN_CROSS_GUARD_HEIGHT = MIN_GRIP_HEIGHT;
    private static final int MAX_CROSS_GUARD_HEIGHT = 128;
    // =================================================================
    private static final int MIN_EDGE_WIDTH = 8;
    private static final int MAX_EDGE_WIDTH = 256;
    // ---
    private static final int MIN_EDGE_HEIGHT = 4;
    private static final int MAX_EDGE_HEIGHT = 128;
    // =================================================================


    public Pixmap generate() {
        Pixmap pommel = new PommelGenerator().generate(12, 12);
        Pixmap grip = new GripGenerator().generate(32, 8, 10);
        Pixmap crossGuard = new CrossGuardGenerator().generate(8, 32, 8);
        Pixmap edge = new EdgeGenerator().generate(96, 12, 12);

        final int swordWidth = pommel.getWidth() + grip.getWidth() + crossGuard.getWidth() + edge.getWidth();
        final int swordHeight = Ints.max(pommel.getHeight(), grip.getHeight(), crossGuard.getHeight(), edge.getHeight());

        final Pixmap result = new Pixmap(swordWidth, swordHeight, Pixmap.Format.RGBA8888);
        result.setColor(Color.CLEAR);
        result.fill();

        if (DEBUG) {
            result.setColor(Color.BLUE);
            result.drawRectangle(0, 0, swordWidth, swordHeight);
        }

        result.setColor(Color.BLACK);

        int offset = 0;
//        result.drawPixmap(pommel, offset, center(swordHeight, pommel.getHeight()));
        offset += pommel.getWidth()/2;
        result.drawPixmap(grip, offset, center(swordHeight, grip.getHeight()));
        result.drawPixmap(pommel, 0, center(swordHeight, pommel.getHeight()));
        offset += grip.getWidth() - 1;
        result.drawPixmap(crossGuard, offset, center(swordHeight, crossGuard.getHeight()));
        offset += crossGuard.getWidth() - 1;
        result.drawPixmap(edge, offset, center(swordHeight, edge.getHeight()));

        return result;
    }

}
