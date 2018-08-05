package ru.belyaev.gen.sword.utils;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import javafx.scene.shape.FillRule;

import java.util.ArrayList;
import java.util.List;

public final class PixmapUtils {
    private PixmapUtils() {
    }

    public static void drawLine(Pixmap pixmap, Vector2 a, Vector2 b) {
        pixmap.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
    }

    public static void drawLine(Pixmap pixmap, Vector2... points) {
        if (points.length >= 2) {
            Vector2 p = points[0];
            for (int i = 1; i < points.length; i++) {
                drawLine(pixmap, p, points[i]);
                p = points[i];
            }
        }
    }

    public static void drawLine(Pixmap pixmap, List<Vector2> points) {
        if (points.size() >= 2) {
            Vector2 p = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                drawLine(pixmap, p, points.get(i));
                p = points.get(i);
            }
        }
    }

    public static void drawPoly(Pixmap pixmap, Vector2... points) {
        if (points.length >= 3) {
            Vector2 p = points[0];
            for (int i = 1; i < points.length; i++) {
                drawLine(pixmap, p, points[i]);
                p = points[i];
            }
            drawLine(pixmap, p, points[0]);
        }
    }

    public static void drawPoints(Pixmap pixmap, List<Vector2> points) {
        for (Vector2 v: points) {
            pixmap.drawPixel((int)v.x,(int)v.y);
        }
    }

    private final static Vector2 tmp = new Vector2();
    public static void drawPoints(Pixmap pixmap, List<Vector2> points, Matrix3 mat) {
        for (Vector2 v: points) {
            tmp.set(v).mul(mat);
            pixmap.drawPixel((int)tmp.x,(int)tmp.y);
        }
    }

    //Matrix3

    public static void fillPoly(Pixmap pixmap, Vector2... points) {
        if (points.length >= 3) {
            int xm = 0;
            int ym = 0;
            for (Vector2 p : points) {
                xm = Math.max(xm, (int) p.x);
                ym = Math.max(ym, (int) p.y);
            }
        }
    }

    public interface PixmapProcessor {
        void process(Pixmap pixmap, int x, int y);
    }

    public static void processPixmap(Pixmap pixmap, PixmapProcessor processor) {
        for (int x = 0, w = pixmap.getWidth(); x < w; x++) {
            for (int y = 0, h = pixmap.getHeight(); y < h; y++) {
                processor.process(pixmap, x, y);
            }
        }
    }

    public static void fillPoints(Pixmap pixmap, List<Vector2> points) {
        for (Vector2 p : points) {
            pixmap.drawPixel((int) p.x, (int) p.y);
        }
    }
}
