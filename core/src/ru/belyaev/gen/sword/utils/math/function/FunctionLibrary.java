package ru.belyaev.gen.sword.utils.math.function;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.google.common.base.Preconditions;

import java.util.Random;
import java.util.function.Function;

public class FunctionLibrary {
    public static Function<Float, Float> sin(int p, int x1, int x2) {
        final float sx = (float) (Math.PI / (x2 - x1) * p);
        return x -> (float) Math.sin(sx * x);
    }

    public static Function<Float, Float> line(Vector2 a, Vector2 b) {
        Preconditions.checkState(a.x != b.x, "Vertical line not supported");
        return x -> (x - a.x) * (b.y - a.y) / (b.x - a.x) + a.y;
    }

//    public static Function<Float, Float> bline(Vector2 a, Vector2 b, Vector2 c) {
//        Bezier<Vector2> bezier = new Bezier<>(a, b, c);
//        bezier.
//        Preconditions.checkState(a.x != b.x, "Vertical line not supported");
//        return x -> (x - a.x) * (b.y - a.y) / (b.x - a.x) + a.y;
//    }


    private float random(float from, float to) {
        if (from == to) {
            return from;
        }
        if (to > from) {
            float temp = from;
            from = to;
            to = temp;
        }
        return from + new Random().nextFloat() * (to - from);
    }

//        public static Function<Float, Float> line(Vector2 a, Vector2 b) {
//        return x -> (float) Math.sin(sx * x);
//    }

    private void bladeGenerator(float width, float length) {
        // final rod; // - функция стержня // прямая линия как частный случай

        final Vector2 a1 = new Vector2(0, -width / 2);
        final Vector2 c = new Vector2(length, random(-width / 2, +width / 2));
        final Vector2 a2 = new Vector2(0, +width / 2);

        final Vector2 a1c = new Vector2(random(a1.x, c.x), random(a1.y, c.y));
        final Vector2 a2c = new Vector2(random(a2.x, c.x), random(a2.y, c.y));
    }
}
