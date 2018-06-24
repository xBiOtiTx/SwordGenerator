package ru.belyaev.gen.sword.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ContourUtils {
    public static List<Vector2> toContour(Vector2 a, Vector2 b) {
        final List<Vector2> result = new ArrayList<>();

        final int x1 = (int) a.x;
        final int y1 = (int) a.y;

        final int x2 = (int) b.x;
        final int y2 = (int) b.y;

        final int dx = x2 - x1;
        final int dy = y2 - y1;
        final float de = Math.abs((float) dy / (float) dx);

        float e = 0;
        int y = y1;
        for (int x = x1, step = (int) Math.signum(x2 - x1); x <= x2; x += step) {
            result.add(new Vector2(x, y));
            e += de;
            while (e >= 0.5) {

            }
        }

//        for x from x1 to x2 {
//            y = y1 + dy * (x - x1) / dx
//            plot(x, y)
//        }

        return result;
    }

    private List<Vector2> lineContourLow(int x0, int y0, int x1, int y1) {
        final List<Vector2> result = new ArrayList<>();

        int dx = x1 - x0;
        int dy = y1 - y0;
        int yi = 1;
        if (dy < 0) {
            yi = -1;
            dy = -dy;
        }
        int d = 2 * dy - dx;

        int y = y0;
        for (int x = x0, xs = (int) Math.signum(dx); x <= x1; x += xs) {
            result.add(new Vector2(x, y));
            if (d > 0) {
                y += yi;
                d -= 2 * dx;
            }
            d += 2 * dy;
        }
        return result;
    }

    private List<Vector2> lineContourHeight(int x0, int y0, int x1, int y1) {
        final List<Vector2> result = new ArrayList<>();

        int dx = x1 - x0;
        int dy = y1 - y0;
        int xi = 1;
        if (dx < 0) {
            xi = -1;
            dx = -dx;
        }
        int d = 2 * dx - dy;

        int x = x0;
        for (int y = y0, ys = (int) Math.signum(dy); y <= y1; y += ys) {
            result.add(new Vector2(x, y));
            if (d > 0) {
                x += xi;
                d -= 2 * dy;
            }
            d += 2 * dx;
        }
        return result;
    }



    private void f(int i1, int i2, int j1, int j2, int di, int dj, float de) {
        float e = 0;
        int j = j1;
        for (int v = i1, s = (int) Math.signum(i2 - i1); v <= i2; v += s) {
            // plot i, j
            e += de;
            while (e >= 0.5) {
                j += Math.signum(dj);
                e -= 1;
            }
        }
    }


    private static final Vector2 UP = new Vector2(0, -1);
    private static final Vector2 UP_RIGHT = new Vector2(1, -1);
    private static final Vector2 RIGHT = new Vector2(1, 0);
    private static final Vector2 RIGHT_DOWN = new Vector2(1, 1);
    private static final Vector2 DOWN = new Vector2(0, 1);
    private static final Vector2 DOWN_LEFT = new Vector2(-1, 1);
    private static final Vector2 LEFT = new Vector2(-1, 0);
    private static final Vector2 LEFT_UP = new Vector2(-1, -1);
    private static final List<Vector2> DIRECTIONS = Arrays.asList(
            UP,
            UP_RIGHT,
            RIGHT,
            RIGHT_DOWN,
            DOWN,
            DOWN_LEFT,
            LEFT,
            LEFT_UP
    );

    public static List<Vector2> fromPixmap(Pixmap pixmap, Vector2 start) {
        final List<Vector2> result = new ArrayList<>();
        result.add(start);

        Vector2 prev = start;
        while (true) {
            final List<Vector2> candidates = new ArrayList<>();
            for (Vector2 d : DIRECTIONS) {
                final Vector2 next = apply(prev, d);
                if (contains(pixmap, next) && !result.contains(next)) {
                    candidates.add(next);
                }
            }
            if (candidates.isEmpty()) {
                break;
            }
            if (candidates.size() > 1) {
                System.out.println("111");
            }

            prev = candidates.get(candidates.size() - 1);
            result.add(prev);
        }

        return result;
    }

    private static boolean contains(Pixmap pixmap, Vector2 start) {
        return pixmap.getPixel((int) start.x, (int) start.y) != 0;
    }

    private static Vector2 apply(Vector2 position, Vector2 direction) {
        final Vector2 result = new Vector2(position);
        result.add(direction);
        return result;
    }


    private static Vector2 rotate(Vector2 a, Vector2 b, int dir) {
        final Vector2 ab = new Vector2(b.x - a.x, b.y - a.y);
        ab.rotate90(dir);
        ab.nor();
        ab.x = Math.round(ab.x);
        ab.y = Math.round(ab.y);
        return ab;
    }

    public static List<Vector2> outline(List<Vector2> contour) {
        final List<Vector2> result = new ArrayList<>();
        out(result, contour, contour.get(1), contour.get(0), -1);
        for (int i = 1, l = contour.size(); i < l; i++) {
            out(result, contour, contour.get(i - 1), contour.get(i), 1);
        }
        return result;
    }

    private static void out(List<Vector2> result, List<Vector2> contour, Vector2 a, Vector2 b, int dir) {
        final Vector2 r = rotate(a, b, dir);
        if (r.x != 0) {
            final Vector2 v = new Vector2(b.x + r.x, b.y);
            if (!contour.contains(v)) {
                result.add(v);
            }
        }
        if (r.y != 0) {
            final Vector2 v = new Vector2(b.x, b.y + r.y);
            if (!contour.contains(v)) {
                result.add(v);
            }
        }
    }

    private static void addAllNotContains(List<Vector2> list, List<Vector2> add) {
        for (Vector2 v : add) {
            if (!list.contains(v)) {
                list.add(v);
            }
        }
    }
}
